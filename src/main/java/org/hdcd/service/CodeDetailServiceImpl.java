package org.hdcd.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeDetail;
import org.hdcd.repository.CodeDetailRepository;
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
}
