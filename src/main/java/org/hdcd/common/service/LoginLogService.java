package org.hdcd.common.service;

import org.hdcd.common.domain.LoginLog;

import java.util.List;

public interface LoginLogService {

    public void register(LoginLog loginLog)throws Exception;

    public List<LoginLog> list()throws Exception;

}
