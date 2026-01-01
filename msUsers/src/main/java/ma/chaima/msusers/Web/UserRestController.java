package ma.chaima.msusers.Web;

import lombok.AllArgsConstructor;
import ma.chaima.msusers.DTOs.UserReqDTO;
import ma.chaima.msusers.DTOs.UserRespDTO;
import ma.chaima.msusers.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {
    private UserService userService;

    @GetMapping
    public List<UserRespDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserRespDTO getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserRespDTO saveUser(@RequestBody UserReqDTO userReqDTO) {
        return userService.addUser(userReqDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
