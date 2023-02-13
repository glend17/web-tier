package org.cse546.controller;

import org.cse546.services.S3Service;
import org.cse546.services.SQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
public class WebController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private SQSService sqsService;

    @GetMapping("/test")
    public String defaultStatus() {
        return "OK";
    }

    @PostMapping("/uploadImagesAndGetResults")
    public String uploadImagesAndGetResults(@RequestParam("files") MultipartFile[] files) {

        //processes the input and waits and fetches all the responses
        List<String> fileNameList = s3Service.saveImagesToS3(files);
        //saved images are sent to SQS request queue
        sqsService.sendSavedImagesToRequestQueue(fileNameList);
        sqsService.receiveSQSResponse(fileNameList);
        return "ok";
    }


}
