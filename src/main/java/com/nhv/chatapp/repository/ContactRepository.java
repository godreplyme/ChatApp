package com.nhv.chatapp.repository;

import com.nhv.chatapp.entity.Contact;
import com.nhv.chatapp.entity.enums.ContactStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    @Query("SELECT COUNT(c) > 0 FROM Contact c WHERE " +
            "((c.user.id = :userId1 AND c.contactUser.id = :userId2) OR " +
            "(c.user.id = :userId2 AND c.contactUser.id = :userId1)) AND " +
            "c.status = :status")
    boolean existsContactUsers(@Param("userId1") String userId1,
                                         @Param("userId2") String userId2,
                                         @Param("status") ContactStatus status);


    @Query("SELECT CASE " +
            "WHEN c.user.id = :currentUserId THEN c.contactUser.id " +
            "ELSE c.user.id END " +
            "FROM Contact c WHERE " +
            "(c.user.id = :currentUserId OR c.contactUser.id = :currentUserId) AND " +
            "c.status = 'ACCEPTED' AND " +
            "(c.user.id IN :userIds OR c.contactUser.id IN :userIds)")
    Set<String> findAcceptedFriendIds(@Param("currentUserId") String currentUserId,
                                      @Param("userIds") List<String> userIds);

    List<Contact> findByUserId(String userId);
}
