package com.nhv.chatapp.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AddMemberRequest {
    List<String> memberIds;
}
