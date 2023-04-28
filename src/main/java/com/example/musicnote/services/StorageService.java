package com.example.musicnote.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.internal.resource.S3ObjectResource;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StorageService {
    private final S3Client client=S3Client.builder().region(Region.AP_SOUTH_1).credentialsProvider(ProfileCredentialsProvider.create()).endpointOverride(URI.create("http://s3.ap-south-1.amazonaws.com")).build();
    public List<String> getSongFileNames(){
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket("sagar-aws-s3")
                .build();
        ListObjectsV2Response response = client.listObjectsV2(listObjectsV2Request);
        List<S3Object> objects = response.contents();
        return objects.stream().map(S3Object::key).collect(Collectors.toList());
    }

    public void uploadSong(MultipartFile file) throws IOException {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket("sagar-aws-s3")
                .key(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();
        client.putObject(objectRequest, RequestBody.fromBytes(file.getBytes()));
    }
}
