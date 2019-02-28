package com.pshc.hepdre.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pshc.hepdre.model.Member;

public interface UserRepository extends JpaRepository<Member, String>{
	
}
