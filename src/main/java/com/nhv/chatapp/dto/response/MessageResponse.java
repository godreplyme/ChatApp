package com.nhv.chatapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhv.chatapp.dto.MemberReadDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
    private String messageId;
    private String content;
    private String senderId;
    private String senderName;
    private String senderUsername;
    private String senderAvatar;
    private Instant sentAt;
    private String messageType;
    private String messageStatus;
    private boolean isLastRead;  // Tin nhắn cuối mà current user đã đọc
    private List<MemberReadDTO> readBy;
    private int readCount;
    private ReplyMessage replyTo;

    @Getter
    @Setter
    @Builder
    public static class ReplyMessage{
        private String messageId;
        private String content;
        private String senderId;
        private String senderName;
    }
}
