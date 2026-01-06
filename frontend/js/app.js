import API from './api.js';

document.addEventListener('DOMContentLoaded', () => {
    initApp();
});

// App State
const state = {
    salles: [],
    users: [],
    reservations: []
};

function initApp() {
    setupNavigation();
    loadDashboardData();
    setupReservationForm();
    setupCreateForms();
}

function setupCreateForms() {
    // Salles
    const btnAddSalle = document.getElementById('btn-add-salle');
    const modalSalle = document.getElementById('modal-salle');
    const formSalle = document.getElementById('form-salle');

    if (btnAddSalle) {
        btnAddSalle.addEventListener('click', () => {
            modalSalle.classList.remove('hidden');
        });
    }

    formSalle.addEventListener('submit', async (e) => {
        e.preventDefault();
        const data = {
            nom: document.getElementById('salle-nom').value,
            capacite: parseInt(document.getElementById('salle-capacite').value),
            equipement: document.getElementById('salle-equipement').value
        };

        try {
            await API.createSalle(data);
            alert('Salle créée avec succès !');
            formSalle.reset();
            modalSalle.classList.add('hidden');
            // Refresh
            state.salles = await API.fetchSalles();
            renderSalles();
            loadDashboardData();
        } catch (err) {
            alert('Erreur lors de la création de la salle');
        }
    });

    // Users
    const btnAddUser = document.getElementById('btn-add-user');
    const modalUser = document.getElementById('modal-user');
    const formUser = document.getElementById('form-user');

    if (btnAddUser) {
        btnAddUser.addEventListener('click', () => {
            modalUser.classList.remove('hidden');
        });
    }

    formUser.addEventListener('submit', async (e) => {
        e.preventDefault();
        const data = {
            nom: document.getElementById('user-nom').value,
            email: document.getElementById('user-email').value
        };

        try {
            await API.createUser(data);
            alert('Utilisateur créé avec succès !');
            formUser.reset();
            modalUser.classList.add('hidden');
            // Refresh
            state.users = await API.fetchUsers();
            renderUsers();
            loadDashboardData();
        } catch (err) {
            alert('Erreur lors de la création de l\'utilisateur');
        }
    });
}

function setupNavigation() {
    const navLinks = document.querySelectorAll('.nav-links li');
    const sections = document.querySelectorAll('.content-section');
    const pageTitle = document.getElementById('page-title');

    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();

            // Remove active class from all links
            navLinks.forEach(l => l.classList.remove('active'));
            // Add active class to clicked link
            link.classList.add('active');

            const targetId = link.getAttribute('data-target');

            // Hide all sections
            sections.forEach(s => s.classList.remove('active'));
            // Show target section
            document.getElementById(targetId).classList.add('active');

            // Update Title
            pageTitle.textContent = link.innerText.trim();

            // Load specific data based on section
            if (targetId === 'salles') renderSalles();
            if (targetId === 'utilisateurs') renderUsers();
            if (targetId === 'reservations') renderReservations();
        });
    });
}

// Data Loading & Rendering
async function loadDashboardData() {
    state.salles = await API.fetchSalles();
    state.users = await API.fetchUsers();
    state.reservations = await API.fetchReservations();

    // Update Stats
    document.getElementById('stats-total-salles').textContent = state.salles.length;
    document.getElementById('stats-total-users').textContent = state.users.length;
    document.getElementById('stats-total-reservations').textContent = state.reservations.length;
}

function renderSalles() {
    const grid = document.getElementById('salles-grid');
    grid.innerHTML = state.salles.map(salle => `
        <div class="room-card" onclick="showSalleDetails(${salle.id})" style="cursor: pointer;">
            <div class="room-image-placeholder">
                <i class="fa-solid fa-people-roof"></i>
            </div>
            <div class="room-details">
                <h3>${salle.nom}</h3>
                <div class="room-meta">
                    <span><i class="fa-solid fa-users"></i> ${salle.capacite} Personnes</span>
                </div>
                ${salle.equipement ? `<div class="room-equipment"><i class="fa-solid fa-plug"></i> ${salle.equipement}</div>` : ''}
            </div>
        </div>
    `).join('');

    // Expose function to window for onclick attribute
    window.showSalleDetails = (id) => {
        const salle = state.salles.find(s => s.id === id);
        if (salle) {
            document.getElementById('detail-salle-nom').textContent = salle.nom;
            document.getElementById('detail-salle-capacite').textContent = salle.capacite;
            document.getElementById('detail-salle-equipement').textContent = salle.equipement || 'Aucun';
            document.getElementById('modal-salle-details').classList.remove('hidden');
        }
    };
}

function renderUsers() {
    const tbody = document.getElementById('users-table-body');
    tbody.innerHTML = state.users.map(user => `
        <tr onclick="showUserDetails(${user.id})" style="cursor: pointer;">
            <td>#${user.id}</td>
            <td>
                <div style="display:flex; align-items:center; gap:0.5rem;">
                    <img src="https://ui-avatars.com/api/?name=${user.nom}&background=random" style="width:30px;height:30px;border-radius:50%">
                    ${user.nom}
                </div>
            </td>
            <td>${user.email}</td>
        </tr>
    `).join('');

    window.showUserDetails = (id) => {
        const user = state.users.find(u => u.id === id);
        if (user) {
            document.getElementById('detail-user-nom').textContent = user.nom;
            document.getElementById('detail-user-email').textContent = user.email;
            document.getElementById('modal-user-details').classList.remove('hidden');
        }
    };
}

function renderReservations() {
    const tbody = document.getElementById('reservations-table-body');

    tbody.innerHTML = state.reservations.map(res => {
        const salleName = state.salles.find(s => s.id === res.salleId)?.nom || `Salle #${res.salleId}`;
        const userName = state.users.find(u => u.id === res.utilisateurId)?.nom || `User #${res.utilisateurId}`;

        return `
        <tr onclick="showReservationDetails(${res.id})" style="cursor: pointer;">
            <td>#${res.id}</td>
            <td>${salleName}</td>
            <td>${userName}</td>
            <td>${res.date}</td>
            <td>${res.heure}</td>
            <td onclick="event.stopPropagation()"> <!-- Stop propagation to avoid opening details when deleting -->
                <button class="btn btn-secondary" onclick="deleteRes(${res.id})" style="padding:0.25rem 0.5rem;font-size:0.8rem;color:var(--danger)">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </td>
        </tr>
    `}).join('');

    window.showReservationDetails = (id) => {
        const res = state.reservations.find(r => r.id === id);
        if (res) {
            const salleName = state.salles.find(s => s.id === res.salleId)?.nom || `Salle #${res.salleId}`;
            const userName = state.users.find(u => u.id === res.utilisateurId)?.nom || `User #${res.utilisateurId}`; // Fixed ID reference

            document.getElementById('detail-res-salle').textContent = salleName;
            document.getElementById('detail-res-user').textContent = userName;
            document.getElementById('detail-res-date').textContent = res.date;
            document.getElementById('detail-res-heure').textContent = res.heure;
            document.getElementById('modal-reservation-details').classList.remove('hidden');
        }
    };

    window.deleteRes = async (id) => {
        if (confirm('Supprimer cette réservation ?')) {
            try {
                await API.deleteReservation(id);
                // Reload
                state.reservations = await API.fetchReservations();
                renderReservations();
                loadDashboardData();
            } catch (err) {
                alert('Erreur lors de la suppression');
            }
        }
    };
}

function setupReservationForm() {
    const btnNew = document.getElementById('btn-new-reservation');
    const btnCancel = document.getElementById('btn-cancel-booking');
    const formContainer = document.getElementById('booking-form-container');
    const form = document.getElementById('booking-form');

    btnNew.addEventListener('click', async () => {
        // Refresh data to ensure selects are up to date
        state.salles = await API.fetchSalles();
        state.users = await API.fetchUsers();

        populateSelects();
        formContainer.classList.remove('hidden');
    });

    btnCancel.addEventListener('click', () => {
        formContainer.classList.add('hidden');
    });

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const data = {
            utilisateurId: document.getElementById('booking-user').value,
            salleId: document.getElementById('booking-room').value,
            date: document.getElementById('booking-date').value,
            heure: document.getElementById('booking-time').value + ":00" // Ensure HH:mm:ss format if backend expects LocalTime string
        };

        try {
            await API.createReservation(data);
            alert('Réservation créée avec succès !');
            form.reset();
            formContainer.classList.add('hidden');

            // Refresh
            state.reservations = await API.fetchReservations();
            renderReservations();
            loadDashboardData();
        } catch (err) {
            alert('Erreur lors de la réservation');
        }
    });
}

function populateSelects() {
    const userSelect = document.getElementById('booking-user');
    const roomSelect = document.getElementById('booking-room');

    userSelect.innerHTML = '<option value="">Sélectionner un utilisateur</option>' +
        state.users.map(u => `<option value="${u.id}">${u.nom}</option>`).join('');

    roomSelect.innerHTML = '<option value="">Sélectionner une salle</option>' +
        state.salles.map(s => `<option value="${s.id}">${s.nom} (${s.capacite} pers.)</option>`).join('');
}
