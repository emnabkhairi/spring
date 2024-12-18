package com.example.SysEve.dao.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.SysEve.dao.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Search for events where the name or category contains the query string
    @Query("SELECT e FROM Event e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.category) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Event> findByNameOrCategoryContaining(@Param("query") String query);

    Page<Event> findByNameContainingOrCategoryContaining(String name, String category, Pageable pageable);
}