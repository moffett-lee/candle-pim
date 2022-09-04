package com.leyuze.repository;

import com.leyuze.entity.CUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CUserRepository extends JpaRepository<CUser,Long> {

    public List<CUser> findByPwd(String pwd);

}
