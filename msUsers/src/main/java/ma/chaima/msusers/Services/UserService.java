package ma.chaima.msusers.Services;

import ma.chaima.msusers.DTOs.UserReqDTO;
import ma.chaima.msusers.DTOs.UserRespDTO;

import java.util.List;

public interface UserService {
    UserRespDTO addUser(UserReqDTO userReqDTO);
    UserRespDTO getUser(Long id);
    List<UserRespDTO> getAllUsers();
    void deleteUser(Long id);
}
