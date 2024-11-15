package com.projects.example.jwt_eg.repo;

import com.projects.example.jwt_eg.modal.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByUsername(String userName);
}
