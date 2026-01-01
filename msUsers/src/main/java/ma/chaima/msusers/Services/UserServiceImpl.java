package ma.chaima.msusers.Services;

import lombok.AllArgsConstructor;
import ma.chaima.msusers.DTOs.UserReqDTO;
import ma.chaima.msusers.DTOs.UserRespDTO;
import ma.chaima.msusers.Entities.Utilisateur;
import ma.chaima.msusers.Mappers.UserMapper;
import ma.chaima.msusers.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserRespDTO addUser(UserReqDTO userReqDTO) {
        Utilisateur utilisateur = userMapper.fromUserReqDTO(userReqDTO);
        Utilisateur savedUser = userRepository.save(utilisateur);
        return userMapper.fromUtilisateur(savedUser);
    }

    @Override
    public UserRespDTO getUser(Long id) {
        Utilisateur utilisateur = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));
        return userMapper.fromUtilisateur(utilisateur);
    }

    @Override
    public List<UserRespDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::fromUtilisateur)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
