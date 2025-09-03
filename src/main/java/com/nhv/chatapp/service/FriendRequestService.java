package com.nhv.chatapp.service;

import com.nhv.chatapp.dto.response.FriendRequestResponse;
import com.nhv.chatapp.entity.enums.FriendRequestStatus;

public interface FriendRequestService {
    FriendRequestResponse sendFriendRequest(String userId);
    void updateFriendRequestStatus(String userId, FriendRequestStatus status);
}
