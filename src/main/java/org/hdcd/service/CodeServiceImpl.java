package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeGroup;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.repository.CodeGroupRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final CodeGroupRepository codeGroupRepository;

    @Override
    public List<CodeLabelValue> getCodeGroupList() throws Exception {
        List<CodeGroup> codeGroups = codeGroupRepository.findAll(Sort.by(Sort.Direction.ASC,"regDate"));

        List<CodeLabelValue> codeGroupList = new ArrayList<>();

        //Label에 그룹코드와 그룹명 담기
        for(CodeGroup codeGroup:codeGroups){
            codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(),codeGroup.getGroupName()));


        }
        return codeGroupList;
    }
}
