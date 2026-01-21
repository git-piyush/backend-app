package com.backend.service;

import com.backend.entity.RefCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RefcodeService {

    RefCode createRefcode(RefCode refcode);

    RefCode getRefCodeById(Long id);

    RefCode updateRefcode(RefCode refCode);

    void deleteRefcode(Long id);

    Map<String, String> findRefCodeByCategory(String refCodeCat);

    Map<String, Map<String, String>> getAllRefcode();

    Page<RefCode> getRefCodes(int page, int size, String sortBy, String direction);

    List getAllRefcodeCategoryList();

    RefCode findByRefCode(String refCode);
}
