package com.nhv.chatapp.repository;

import com.nhv.chatapp.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    Page<Message> findByChatRoomId(String chatRoomId, Pageable pageable);

    @Query("SELECT m FROM Message m " +
            "WHERE m.chatRoom.id = :chatRoomId " +
            "AND m.sender.id != :userId " +
            "AND NOT EXISTS (SELECT mr FROM Messageread mr WHERE mr.message.id = m.id AND mr.user.id = :userId) " +
            "ORDER BY m.sentAt ASC")
    List<Message> findUnreadMessagesByUserAndChatRoom(@Param("userId") String userId,
                                                      @Param("chatRoomId") String chatRoomId);

}
