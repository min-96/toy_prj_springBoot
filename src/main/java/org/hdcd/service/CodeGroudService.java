package org.hdcd.service;

import org.hdcd.domain.CodeGroup;

import java.util.List;

public interface CodeGroudService {

  public void register(CodeGroup codeGroup)throws Exception;

  public List<CodeGroup> list() throws Exception;

  public  CodeGroup read(String groupCode) throws Exception;

  public void edit(CodeGroup codeGroup) throws  Exception;

  void remove(String groupCode) throws Exception;
}
