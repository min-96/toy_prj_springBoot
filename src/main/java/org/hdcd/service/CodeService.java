package org.hdcd.service;

import org.hdcd.dto.CodeLabelValue;

import java.util.List;

public interface CodeService {

    List<CodeLabelValue> getCodeGroupList() throws Exception;

}
