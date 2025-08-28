package com.nhv.chatapp.repository;

import com.nhv.chatapp.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<Chatroom, String> {

    @Query("""
        SELECT DISTINCT cr FROM Chatroom cr
        JOIN Userchatroom ucr1 ON cr.id = ucr1.chatRoom.id
        JOIN Userchatroom ucr2 ON cr.id = ucr2.chatRoom.id
        WHERE cr.type = 'PRIVATE'
        AND ucr1.user.id = :userId1
        AND ucr2.user.id = :userId2
        AND (SELECT COUNT(ucr3) FROM Userchatroom ucr3 WHERE ucr3.chatRoom.id = cr.id) = 2
    """)
    Optional<Chatroom> findExistingPrivateChat(@Param("userId1") String userId1, @Param("userId2") String userId2);
    @Query("""
        SELECT CASE WHEN COUNT(cr) > 0 THEN true ELSE false END
        FROM Chatroom cr
        JOIN Userchatroom ucr1 ON cr.id = ucr1.chatRoom.id
        JOIN Userchatroom ucr2 ON cr.id = ucr2.chatRoom.id
        WHERE ucr1.user.id = :userId1 
          AND ucr2.user.id = :userId2 
          AND cr.type = 'PRIVATE'
    """)
    boolean existsPrivateChatroomByUserIds(
            @Param("userId1") String userId1,
            @Param("userId2") String userId2
    );
}