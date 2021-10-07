package org.hdcd.common.repository;

import org.hdcd.common.domain.PerfomanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfomanceLogRepository extends JpaRepository<PerfomanceLog,Long> {
}
