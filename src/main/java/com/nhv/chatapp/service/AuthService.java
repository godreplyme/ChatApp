package com.nhv.chatapp.service;

import com.nhv.chatapp.dto.UserAuthDTO;
import com.nhv.chatapp.dto.request.ChangePasswordRequest;
import com.nhv.chatapp.dto.request.LoginRequest;
import com.nhv.chatapp.dto.request.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    UserAuthDTO login(LoginRequest loginRequest);
}
