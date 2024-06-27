package iuh.fit.salesappbackend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Upload {

    @Value("${aws-s3.bucket-name}")
    private String bucketName;

    private final S3TransferManager transferManager;

    public String uploadFile(MultipartFile multipartFile) throws IOException {

        Path tempFile = Files.createTempFile("temp", multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        String key = generateUniqueKey(multipartFile.getOriginalFilename());

        UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                .putObjectRequest(b -> b.bucket(bucketName).key(key))
                .source(tempFile)
                .build();

        FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);

        fileUpload.completionFuture().join();
        return "https://" + bucketName + ".s3." + "ap-southeast-1" + ".amazonaws.com/" + key;
    }

    public String generateUniqueKey(String originalFileName) {
//        return System.currentTimeMillis() + "_" + originalFileName;
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

}
