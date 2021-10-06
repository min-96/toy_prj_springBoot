package org.hdcd.common.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.common.domain.AccessLog;
import org.hdcd.common.repository.AccessLogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccessLogServiceImpl implements AccessLogService {

   private final AccessLogRepository accessLogRepository;
    @Override
    public void register(AccessLog accessLog) throws Exception {
    accessLogRepository.save(accessLog);
    }

    @Override
    public List<AccessLog> list() throws Exception {
        return accessLogRepository.findAll(Sort.by(Sort.Direction.DESC,"logNo"));
    }
}
