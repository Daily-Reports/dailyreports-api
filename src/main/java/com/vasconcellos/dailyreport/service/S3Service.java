package com.vasconcellos.dailyreport.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        amazonS3.putObject(bucketName, fileName, file.getInputStream(), null);

        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    public InputStream downloadFile(String url) {
        S3Object s3Object = amazonS3.getObject(bucketName, url);

        return s3Object.getObjectContent();
    }

    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }
}