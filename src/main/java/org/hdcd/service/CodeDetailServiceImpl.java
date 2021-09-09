package org.hdcd.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hdcd.repository.CodeDetailRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeDetailServiceImpl implements CodeDetailService{

    private final CodeDetailRepository codeDetailRepository;
}
