package com.example.library.repository;

import com.example.library.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class SyncRepository{

    private final JdbcTemplate jdbcTemplate;

    public Long getCurrentSyncVersion() {
        return jdbcTemplate.queryForObject("""
            SELECT current_value
            FROM sys.sequences
            WHERE name = 'global_sync_version'
            """, Long.class);
    }
}