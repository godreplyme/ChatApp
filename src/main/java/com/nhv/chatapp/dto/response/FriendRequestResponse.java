package com.nhv.chatapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendRequestResponse {
    private String requesterId;
    private String requesterUsername;
    private String recipientId;
    private String recipientUsername;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
