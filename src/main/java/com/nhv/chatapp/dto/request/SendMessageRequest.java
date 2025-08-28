package com.nhv.chatapp.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendMessageRequest {
    private String content;
    private String messageType;
    private String replyToMessageId;
}
