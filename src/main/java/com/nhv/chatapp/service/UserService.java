package com.nhv.chatapp.service;

import com.nhv.chatapp.dto.UserProfileDTO;
import com.nhv.chatapp.dto.UserSummaryDTO;
import com.nhv.chatapp.dto.response.PageResponse;

import java.util.List;

public interface UserService {
    PageResponse<UserSummaryDTO> getUsers(String keyword, int page, int size);
    UserProfileDTO getUserProfile(String userId);
    UserProfileDTO getUserProfile();
    UserProfileDTO updateUser(String userId, UserProfileDTO user);
    void deleteUser(String userId);

}
