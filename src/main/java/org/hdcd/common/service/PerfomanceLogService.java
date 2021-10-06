package org.hdcd.common.service;

import org.hdcd.common.domain.PerfomanceLog;

public interface PerfomanceLogService {
    //서비스 성능 로깅처리
    public void register(PerfomanceLog perfomanceLog);

}
