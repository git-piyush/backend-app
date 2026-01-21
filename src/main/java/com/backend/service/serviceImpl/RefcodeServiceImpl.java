package com.backend.service.serviceImpl;

import com.backend.entity.RefCode;
import com.backend.exceptions.NotFoundException;
import com.backend.repositories.RefcodeRepository;
import com.backend.service.RefcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RefcodeServiceImpl implements RefcodeService {

    @Autowired
    private RefcodeRepository refcodeRepository;

    @Override
    public RefCode createRefcode(RefCode refcode) {
        return refcodeRepository.save(refcode);
    }

    @Override
    public RefCode getRefCodeById(Long id) {
        RefCode refCode = refcodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("RefCode Not Found"));
        return refCode;
    }

    @Override
    public RefCode updateRefcode(RefCode refCode) {
        return refcodeRepository.save(refCode);
    }

    @Override
    public void deleteRefcode(Long id) {
        RefCode refCode = refcodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("RefCode Not Found"));

        if (refCode != null) {
            refcodeRepository.deleteById(id);
        }
    }

    @Override
    public Map<String, String> findRefCodeByCategory(String refCodeCat) {
        Map<String, String> map = refcodeRepository
                .findByCategory(refCodeCat)
                .stream()
                .collect(Collectors.toMap(
                        RefCode::getRefCode,      // key mapper
                        RefCode::getLongName   // value mapper
                ));

        return map;
    }

    @Override
    public Map<String, Map<String, String>> getAllRefcode() {

        List<RefCode> refCodeList = refcodeRepository.findAll();

        Map<String, Map<String, String>> map1 = refCodeList.stream()
                .collect(Collectors.groupingBy(
                        RefCode::getCategory, // outer key: category
                        Collectors.toMap(
                                RefCode::getRefCode,    // inner key: refCode
                                RefCode::getLongName    // inner value: longName
                        )
                ));

        return map1;
    }

    @Override
    public Page<RefCode> getRefCodes(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return refcodeRepository.findAll(pageable);

    }

    @Override
    public List getAllRefcodeCategoryList() {

        List<String> categoryList = refcodeRepository.findAll()
                .stream()
                .map(refcode -> refcode.getCategory())  // transform each refcode to its category
                .collect(Collectors.toList());
        return categoryList;
    }

    @Override
    public RefCode findByRefCode(String refCode) {
        RefCode dbRefCode = refcodeRepository.findByRefCode(refCode);
        return dbRefCode;
    }
}