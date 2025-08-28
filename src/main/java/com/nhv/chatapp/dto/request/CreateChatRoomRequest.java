package com.nhv.chatapp.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CreateChatRoomRequest {
    private List<String> memberIds;
    private String groupName;
}
