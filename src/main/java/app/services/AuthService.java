package app.services;

import app.models.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);

    void login(RegisterUserServiceModel model) throws Exception;
}
