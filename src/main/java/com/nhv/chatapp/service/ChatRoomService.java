package com.nhv.chatapp.service;

import com.nhv.chatapp.dto.ChatRoomMemberDTO;
import com.nhv.chatapp.dto.request.AddMemberRequest;
import com.nhv.chatapp.dto.request.CreateChatRoomRequest;

import com.nhv.chatapp.dto.response.ChatRoomResponse;
import java.util.List;

public interface ChatRoomService {
    ChatRoomResponse createChatRoom(CreateChatRoomRequest createChatRoomRequest);
    List<ChatRoomResponse> getChatRooms();
    List<ChatRoomMemberDTO> addMemberToChatRoom(String chatRoomId, AddMemberRequest addMemberRequest);
    List<ChatRoomMemberDTO> getChatRoomMembers(String chatRoomId);
}
