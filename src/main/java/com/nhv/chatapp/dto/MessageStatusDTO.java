package com.nhv.chatapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageStatusDTO {
    private String chatRoomId;
    private String messageId;
    private List<String> memberReadIds;
}
