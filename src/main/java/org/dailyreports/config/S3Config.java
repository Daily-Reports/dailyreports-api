package org.dailyreports.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 amazonS3() {
        AmazonS3ClientBuilder s3ClientBuilder = AmazonS3ClientBuilder
                .standard()
                .withRegion(region);

        if (!accessKeyId.isEmpty() && !secretAccessKey.isEmpty())
            s3ClientBuilder.withCredentials(new AWSStaticCredentialsProvider
                    (new BasicAWSCredentials(accessKeyId, secretAccessKey)));

        return s3ClientBuilder.build();
    }
}