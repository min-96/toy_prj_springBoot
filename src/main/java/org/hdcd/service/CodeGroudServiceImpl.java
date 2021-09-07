package org.hdcd.service;

import lombok.RequiredArgsConstructor;
import org.hdcd.repository.CodeGroupRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeGroudServiceImpl implements CodeGroudService {

    private final CodeGroupRepository repository;
}


