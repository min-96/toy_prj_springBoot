package org.hdcd.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeDetail;
import org.hdcd.domain.CodeDetailId;
import org.hdcd.repository.CodeDetailRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeDetailServiceImpl implements CodeDetailService{

    private final CodeDetailRepository codeDetailRepository;

    @Override
    //정렬
    public void register(CodeDetail codeDetail) {
        String groupCode = codeDetail.getGroupCode();
        List<Object[]> rsList = codeDetailRepository.getMaxSortSeq(groupCode);
        Integer maxSortSeq = 0;

        if(rsList.size()>0){
            //제일큰값
            Object[] maxValues = rsList.get(0);
            System.out.println(maxValues);
            if(maxValues!=null&& maxValues.length>0){
                maxSortSeq =(Integer)maxValues[0];
            }
        }
        codeDetail.setSortSeq(maxSortSeq+1);
        codeDetailRepository.save(codeDetail);
    }

    @Override
    public List<CodeDetail> list() {
        return codeDetailRepository.findAll(Sort.by(Sort.Direction.ASC,"groupCode","codeValue"));
    }

    @Override
    public CodeDetail read(CodeDetail codeDetail) throws Exception {
       CodeDetailId codeDetailId = new CodeDetailId();
       codeDetailId.setCodeValue(codeDetail.getCodeValue());
       codeDetailId.setGroupCode(codeDetail.getGroupCode());

        return codeDetailRepository.getOne(codeDetailId);
    }

    @Override
    public void edit(CodeDetail codeDetail) {
        CodeDetailId codeDetailId = new CodeDetailId();
        codeDetailId.setCodeValue(codeDetail.getCodeValue());
        codeDetailId.setGroupCode(codeDetail.getGroupCode());

        CodeDetail codeDetailEntity = codeDetailRepository.getOne(codeDetailId);

        codeDetailEntity.setCodeValue(codeDetail.getCodeValue());
        codeDetailEntity.setCodeName(codeDetail.getCodeName());
       // codeDetailEntity.setGroupCode(codeDetail.getGroupCode());

        codeDetailRepository.save(codeDetailEntity);
    }
}
