package net.bulldozer.tourofall.dest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.dest.dto.TodayDestination;

public interface TodayDestinationRepository extends JpaRepository<TodayDestination, Long> {
}
