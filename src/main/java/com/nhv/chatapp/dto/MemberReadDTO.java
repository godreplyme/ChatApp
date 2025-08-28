package com.nhv.chatapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class MemberReadDTO {
    private String userId;
    private String name;
    private Instant readAt;
}
