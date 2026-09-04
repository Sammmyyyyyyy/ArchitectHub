package net.dipesh.archFlow.ArchitectHub.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dipesh.archFlow.ArchitectHub.entity.User;
import net.dipesh.archFlow.ArchitectHub.enums.UserRole;
import net.dipesh.archFlow.ArchitectHub.exceptions.UserNotFoundException;
import net.dipesh.archFlow.ArchitectHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new  UserNotFoundException(id));
    }

    @Transactional
    public void deleteUser(Long id){

        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }

        else{
            throw new UserNotFoundException(id);
        }
    }

    @Transactional
    public User makeAdmin(Long id){

        User user =  userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.setRole(UserRole.ADMIN);

        return userRepository.save(user);
    }

}
