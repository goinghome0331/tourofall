package net.bulldozer.tourofall.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.user.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
