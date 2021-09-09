package org.hdcd.service;

import org.hdcd.domain.CodeDetail;

import java.util.List;

public interface CodeDetailService {
    void register(CodeDetail codeDetail) throws Exception;

    public List<CodeDetail> list();
}
