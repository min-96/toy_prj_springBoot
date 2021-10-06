package org.hdcd.common.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.common.domain.PerfomanceLog;
import org.hdcd.common.repository.PerfomanceLogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceLogService{
   private final PerfomanceLogRepository perfomanceLogRepository;

    @Override
    public void register(PerfomanceLog perfomanceLog) {
        perfomanceLogRepository.save(perfomanceLog);
    }
}
