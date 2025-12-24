package com.entrophy.entrophy_back.mediaStorage.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AzureBlobStorageService {

    private final BlobServiceClient blobServiceClient;

    public String upload(String containerName,
                         String blobName,
                         InputStream data,
                         long size,
                         String contentType) {

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        blobClient.upload(data, size, true);

        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));

        return blobClient.getBlobUrl();
    }

    public void delete(String containerName, String blobName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        if (blobClient.exists()) blobClient.delete();
    }
}
