package com.kiran.SpringbootProject;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);


	Optional<User> findByPasswordAndEmail(String password, String email);


	User findByEmailOrId(String email, Integer id);


}