package app.services;

import app.entity.User;
import app.models.RegisterUserServiceModel;
import app.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.function.ToDoubleBiFunction;

@Service
public class AuthServiceImp implements AuthService {
    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final HashingService hashingService;

    public AuthServiceImp(AuthValidationService authValidationService, UserRepository userRepository, ModelMapper mapper, HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if(!authValidationService.isValid(model)) {
            //TODO
            return;
        }
        User user = mapper.map(model, User.class);

        user.setPassword(hashingService.hash(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void login(RegisterUserServiceModel model) throws Exception {
        String passwordHash = hashingService.hash(model.getPassword());
        if(!userRepository.existsByUsernameAndPassword(model.getUsername(), passwordHash)) {
            throw new Exception("Invalid user");
        }
    }
}
