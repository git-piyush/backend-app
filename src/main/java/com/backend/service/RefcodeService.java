package com.backend.service;

import com.backend.entity.RefCode;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RefcodeService {

    RefCode createRefcode(RefCode refcode);

    RefCode getRefCodeById(Long id);

    RefCode updateRefcode(RefCode refCode);

    void deleteRefcode(Long id);

    Map<String, String> findRefCodeByCategory(String refCodeCat);

    Map<String, Map<String, String>> getAllRefcode();
}
