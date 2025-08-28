package com.nhv.chatapp.repository;

import com.nhv.chatapp.entity.Userchatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserChatRoomRepository extends JpaRepository<Userchatroom, String> {
    List<Userchatroom> findByChatRoomId(String chatRoomId);
    boolean existsByUserIdAndChatRoomId(String userId, String chatRoomId);
    @Query("SELECT ucr FROM Userchatroom ucr " +
            "JOIN FETCH ucr.chatRoom cr " +
            "LEFT JOIN FETCH cr.lastMessage lm " +
            "LEFT JOIN FETCH lm.sender " +
            "WHERE ucr.user.id = :userId " +
            "ORDER BY cr.updateAt DESC")
    List<Userchatroom> findByUserIdOrderByJoinedAtDesc(@Param("userId") String userId);

    @Query("SELECT COUNT(ucr) FROM Userchatroom ucr WHERE ucr.chatRoom.id = :chatRoomId")
    int countByChatRoomId(@Param("chatRoomId") String chatRoomId);

    Userchatroom findByUserIdAndChatRoomId(String userId, String chatRoomId);

    @Query("SELECT ucr.user.id FROM Userchatroom ucr " +
            "WHERE ucr.chatRoom.id = :chatRoomId AND ucr.user.id IN :userIds")
    Set<String> findUserIdsByChatRoomId(@Param("chatRoomId") String chatRoomId,
                                        @Param("userIds") List<String> userIds);

    @Query("SELECT ucr FROM Userchatroom ucr " +
            "JOIN FETCH ucr.user u " +
            "LEFT JOIN FETCH ucr.lastReadMessage lrm " +
            "WHERE ucr.chatRoom.id = :chatRoomId")
    List<Userchatroom> findByChatRoomIdWithUserAndLastRead(@Param("chatRoomId") String chatRoomId);
}
