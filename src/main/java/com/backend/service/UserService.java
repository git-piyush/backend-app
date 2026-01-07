package com.backend.service;

import com.backend.dto.LoginRequest;
import com.backend.dto.RegistrationRequest;
import com.backend.dto.Response;
import com.backend.dto.UserDTO;
import com.backend.entity.User;

public interface UserService {

    Response registerUser(RegistrationRequest registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getOwnAccountDetails();
    User getCurrentLoggedInUser();
    Response updateOwnAccount(UserDTO userDTO);
    Response deleteOwnAccount();
}
