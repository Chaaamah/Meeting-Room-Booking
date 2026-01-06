const BASE_URL = 'http://localhost:8080/api';

const API = {
    async fetchSalles() {
        try {
            const response = await fetch(`${BASE_URL}/salles`);
            if (!response.ok) throw new Error('Erreur lors du chargement des salles');
            return await response.json();
        } catch (error) {
            console.error(error);
            return [];
        }
    },

    async createSalle(salleData) {
        try {
            const response = await fetch(`${BASE_URL}/salles`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(salleData)
            });
            if (!response.ok) throw new Error('Impossible de créer la salle');
            return await response.json();
        } catch (error) {
            console.error(error);
            throw error;
        }
    },

    async fetchUsers() {
        try {
            const response = await fetch(`${BASE_URL}/users`);
            if (!response.ok) throw new Error('Erreur lors du chargement des utilisateurs');
            return await response.json();
        } catch (error) {
            console.error(error);
            return [];
        }
    },

    async createUser(userData) {
        try {
            const response = await fetch(`${BASE_URL}/users`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(userData)
            });
            if (!response.ok) throw new Error('Impossible de créer l\'utilisateur');
            return await response.json();
        } catch (error) {
            console.error(error);
            throw error;
        }
    },

    async fetchReservations() {
        try {
            const response = await fetch(`${BASE_URL}/reservations`);
            if (!response.ok) throw new Error('Erreur lors du chargement des réservations');
            return await response.json();
        } catch (error) {
            console.error(error);
            return [];
        }
    },

    async createReservation(reservationData) {
        try {
            const response = await fetch(`${BASE_URL}/reservations`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reservationData)
            });
            if (!response.ok) throw new Error('Impossible de créer la réservation');
            return await response.json();
        } catch (error) {
            console.error(error);
            throw error;
        }
    },

    async deleteReservation(id) {
        try {
            const response = await fetch(`${BASE_URL}/reservations/${id}`, {
                method: 'DELETE'
            });
            if (!response.ok) throw new Error('Impossible de supprimer la réservation');
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    }
};

export default API;
