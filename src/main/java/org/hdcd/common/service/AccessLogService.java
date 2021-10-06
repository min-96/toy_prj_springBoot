package org.hdcd.common.service;

import org.hdcd.common.domain.AccessLog;

import java.util.List;

public interface AccessLogService {

     void register(AccessLog accessLog)throws Exception;
     List<AccessLog> list() throws Exception;
}
