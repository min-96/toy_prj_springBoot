package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeGroup;
import org.hdcd.repository.CodeGroupRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeGroudServiceImpl implements CodeGroudService {

    private final CodeGroupRepository repository;

    @Override
    public void register(CodeGroup codeGroup) throws Exception {
        repository.save(codeGroup);
    }

    @Override
    public List<CodeGroup> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"regDate"));
    }

    @Override
    public CodeGroup read(String groupCode) throws Exception {
        return repository.getOne(groupCode);
    }

    @Override
    public void edit(CodeGroup codeGroup) throws Exception {
     CodeGroup codeGroupEntity = repository.getOne(codeGroup.getGroupCode());

    // codeGroupEntity.setGroupCode(codeGroup.getGroupCode());
     codeGroupEntity.setGroupName(codeGroup.getGroupName());

     repository.save(codeGroupEntity);
    }
}


