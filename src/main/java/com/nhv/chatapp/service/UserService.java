package com.nhv.chatapp.service;

import com.nhv.chatapp.dto.UserProfileDTO;
import com.nhv.chatapp.dto.UserSummaryDTO;

import java.util.List;

public interface UserService {
    List<UserSummaryDTO> getUsers();
    UserProfileDTO getUserProfile(String userId);
    UserProfileDTO getUserProfile();
    UserProfileDTO updateUser(String userId, UserProfileDTO user);
    void deleteUser(String userId);

}
