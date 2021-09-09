package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeDetail;
import org.hdcd.domain.CodeGroup;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.repository.CodeDetailRepository;
import org.hdcd.repository.CodeGroupRepository;
import org.hdcd.repository.MemberRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final CodeGroupRepository codeGroupRepository;
    private final CodeDetailRepository codeDetailRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<CodeLabelValue> getCodeGroupList() throws Exception {
        List<CodeGroup> codeGroups = codeGroupRepository.findAll(Sort.by(Sort.Direction.ASC,"groupCode"));

        List<CodeLabelValue> codeGroupList = new ArrayList<>();

        //Label에 그룹코드와 그룹명 담기
        for(CodeGroup codeGroup:codeGroups){
            codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(),codeGroup.getGroupName()));


        }
        return codeGroupList;
    }

    @Override
    public List<CodeLabelValue> getCodeList(String classCode) {

        //List<CodeDetail> codeDetail1 = codeDetailRepository.findAllById(classCode);
        List<CodeDetail> codeDetailOptional = codeDetailRepository.findById(classCode);


         //   List<CodeDetail> codeDetails = codeDetailRepository.findAll(Sort.by(Sort.Direction.ASC,"groupValue"));

            List<CodeLabelValue> codeDetailList = new ArrayList<>();

            for(CodeDetail codeDetail:codeDetailOptional){
                codeDetailList.add(new CodeLabelValue(codeDetail.getCodeValue(),codeDetail.getCodeName()));
            }
        return codeDetailList;
    }
}
