package com.nhv.chatapp.dto.response;

import com.nhv.chatapp.dto.ContactDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ContactsResponse {
    private List<ContactDTO> contacts;
    private long totalContacts;


}
