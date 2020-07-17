package app.services;

import app.models.RegisterUserServiceModel;
import org.springframework.stereotype.Service;
import app.repository.UserRepository;

@Service
public class AuthValidationServiceImp implements AuthValidationService {
    private final UserRepository userRepository;

    public AuthValidationServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean isValid(RegisterUserServiceModel model) {
        return this.isEmailValid(model.getEmail()) &&
                this.isUsernameFree(model.getUsername()) &&
                this.arePasswordsValid(model.getPassword(), model.getConfirmPassword()) ;
    }

    private boolean isEmailValid(String email) {
        return true;
    }

    private  boolean isUsernameFree(String username) {
        return !userRepository.existsByUsername(username);
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
