package com.rotana.springmvc.repository;

import com.rotana.springmvc.domain.User;
import com.rotana.springmvc.dto.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Transactional
    @Modifying(clearAutomatically = true)
//    @Modifying(flushAutomatically = true)
    @Query("UPDATE User u SET u.active = :status WHERE u.id = :id ")
    int updateUserStatus(@Param("status") boolean status, @Param("id") Integer id);

    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%', :keyword, '%')")
    List<User> searchByNameConcat(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u WHERE u.name LIKE :keyword")
    List<User> searchByName(@Param("keyword") String Keyword);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword%")
    List<User> searchByNameContains(@Param("keyword") String keyword);

//    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% ESCAPE '/'")
//    List<User> searchByNameUnderscore(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:#{escape(#keyword)}% ESCAPE ?#{escapeCharacter()}")
    List<User> searchByNameUnderscore(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(:#{'%' + escape(#keyword) + '%'}) ESCAPE ?#{escapeCharacter()}")
    List<UserResponse> searchByNameIgnoreCase(@Param("keyword") String keyword);

    // cruds :
}
