package com.nhv.chatapp.service.impl;

import com.nhv.chatapp.dto.UserProfileDTO;
import com.nhv.chatapp.dto.UserSummaryDTO;
import com.nhv.chatapp.entity.User;
import com.nhv.chatapp.exception.BadRequestException;
import com.nhv.chatapp.exception.ResourceNotFoundException;
import com.nhv.chatapp.repository.UserRepository;
import com.nhv.chatapp.service.UserService;
import com.nhv.chatapp.utils.SecurityUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserSummaryDTO> getUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserSummaryDTO> usersDTO = new ArrayList<>();
        for(User user : users) {
            UserSummaryDTO dto = UserSummaryDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername()).build();
            usersDTO.add(dto);
        }
        return usersDTO;
    }

    public UserProfileDTO getUserProfile(String userId){
        User user = this.userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found"));
        return UserProfileDTO.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio())
                .avatar(user.getAvatar()).build();
    }

    public UserProfileDTO getUserProfile(){
        Authentication authentication = SecurityUtils.getAuthentication();
//        System.out.println(authentication);
//        String name = authentication.getName();
//        System.out.println(name);
//        Jwt jwt = (Jwt) authentication.getPrincipal();
//        System.out.println(jwt.getClaims());
        User user = this.userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserProfileDTO.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio())
                .avatar(user.getAvatar()).build();
    }

    public UserProfileDTO updateUser(String userId, UserProfileDTO userProfileDTO){
        User user = this.userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found"));
        user = User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio())
                .avatar(user.getAvatar()).build();
        this.userRepository.save(user);
        return userProfileDTO;
    }

    @Override
    public void deleteUser(String userId) {
        this.userRepository.deleteById(userId);
    }
}
