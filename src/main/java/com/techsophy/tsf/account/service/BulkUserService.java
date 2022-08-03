package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.BulkUploadResponse;
import com.techsophy.tsf.account.dto.BulkUploadSchema;
import com.techsophy.tsf.account.dto.BulkUserResponse;
import com.techsophy.tsf.account.dto.PaginationResponsePayload;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface BulkUserService
{
    BulkUploadResponse bulkUploadUsers(MultipartFile file) throws IOException;

    BulkUploadResponse bulkUpdateStatus(BulkUploadSchema bulkUploadSchema) throws JsonProcessingException;

    List<BulkUserResponse> getAllBulkUsers(String filterColumn, String filterValue, String sortBy, String sortOrder);

    PaginationResponsePayload getAllBulkUsers(String filterColumn, String filterValue,String sortBy,String sortOrder,Pageable pageable);

    List<BulkUserResponse> getAllBulkUsers(String q,String sortBy,String sortOrder);

    PaginationResponsePayload getAllBulkUsers(String q,String sortBy,String sortOrder,Pageable pageable);

    void  deleteBulkUserById(String id);
}
