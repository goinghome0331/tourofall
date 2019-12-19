package net.bulldozer.tourofall.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.user.dto.UserPreference;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
		List<UserPreference> findByUserId(Long userId);
}
