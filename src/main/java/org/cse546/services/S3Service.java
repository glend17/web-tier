package org.cse546.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.cse546.utility.AWSUtility;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3Service {

    private static final Logger logger = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AWSUtility awsUtility;

    public List<String> saveImagesToS3(MultipartFile[] files) {
        List<String> fileNames = storeImagesToS3StorageBucket(files);
        return fileNames;
    }

    private List<String> storeImagesToS3StorageBucket(MultipartFile[] files){
        List<String> fileNameList = new ArrayList<String>();
        for (MultipartFile uploadedFile : files) {
            String fileName = uploadedFile.getOriginalFilename();
            String keyName = fileName;
            InputStream is = null;
            try {
                is = uploadedFile.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ObjectMetadata metadata = null;
            //logger.info("Saving Image to s3 bucket: {} keyname: {} metadata: {}",keyName,metadata);
            awsUtility.getAmazonS3StorageBucket().putObject(awsUtility.getImageBucketName(), keyName, is, metadata);
            fileNameList.add(fileName);
        }

        return fileNameList;
    }
}
