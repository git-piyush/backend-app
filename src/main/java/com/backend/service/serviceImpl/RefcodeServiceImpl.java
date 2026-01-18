package com.backend.service.serviceImpl;

import com.backend.entity.RefCode;
import com.backend.exceptions.NotFoundException;
import com.backend.repositories.RefcodeRepository;
import com.backend.service.RefcodeService;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(()-> new NotFoundException("RefCode Not Found"));
        return refCode;
    }

    @Override
    public RefCode updateRefcode(RefCode refCode) {
        return refcodeRepository.save(refCode);
    }

    @Override
    public void deleteRefcode(Long id) {
        RefCode refCode = refcodeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("RefCode Not Found"));

        if(refCode!=null){
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

        List<RefCode> refCodeList =  refcodeRepository.findAll();

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
}
