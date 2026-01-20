package com.backend.controller;

import com.backend.dto.Response;
import com.backend.entity.Event;
import com.backend.entity.RefCode;
import com.backend.service.RefcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/refcode")
@RequiredArgsConstructor
public class RefCodeController {

    @Autowired
    private RefcodeService refcodeService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody RefCode refCode) {
        try{
            RefCode refCode1 = refcodeService.createRefcode(refCode);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder().status(200).message("Recode created successfully.").build();
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody RefCode refCode) {
        try{
            RefCode refCode1 = refcodeService.updateRefcode(refCode);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder().status(200).message("Recode Updated successfully.").build();
        return ResponseEntity.ok(res);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity getRefcode(@PathVariable Long id) {
        RefCode refCode = null;
        try{
            refCode = refcodeService.getRefCodeById(id);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder()
                .status(200)
                .message("Recode Updated successfully.")
                .refCode(refCode)
                .build();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getRecodeMap/{refCodeCat}")
    public ResponseEntity getRefcodeByCategory(@PathVariable String refCodeCat) {
        Map<String, String> map = null;
        try{
            map = refcodeService.findRefCodeByCategory(refCodeCat);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder()
                .status(200)
                .message("Recode Updated successfully.")
                .refCodeMap(map)
                .build();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllRefcodeMap")
    public ResponseEntity getAllRefcode() {
        Map<String, Map<String,String>> map=null;
        try{
            map = refcodeService.getAllRefcode();
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder()
                .status(200)
                .message("Recode Updated successfully.")
                .refCodeMap1(map)
                .build();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity deleteRefcode(@PathVariable Long id) {
        RefCode refCode = null;
        try{
            refCode = refcodeService.getRefCodeById(id);
            if(refCode!=null){
                refcodeService.deleteRefcode(id);
            }
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder()
                .status(200)
                .message("RefCode successfully.")
                .build();
        return ResponseEntity.ok(res);
    }


    @GetMapping("/refcode-list")
    public Page<RefCode> getRefCodes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortBy", defaultValue = "refCode") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        return refcodeService.getRefCodes(page, size, sortBy, direction);
    }



}
