package com.techsophy.tsf.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.BulkUploadResponse;
import com.techsophy.tsf.account.dto.BulkUploadSchema;
import com.techsophy.tsf.account.model.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import static com.techsophy.tsf.account.constants.AccountConstants.*;

@RequestMapping(ACCOUNTS_URL + VERSION_V1 + USERS_URL)
public interface BulkUserController
{
    @PostMapping(value =BULK_UPLOAD_URL,consumes={MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<BulkUploadResponse> bulkUploadUsers(@RequestPart(value= FILE) MultipartFile file) throws IOException;

    @PostMapping(BULK_STATUS_URL)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<BulkUploadResponse> bulkUpdateStatus(@RequestBody @Validated BulkUploadSchema bulkSchema) throws JsonProcessingException;

    @GetMapping(BULK_URL)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<Void> getAllBulkUsers(@RequestParam(value = QUERY,required = false) String q,
                                      @RequestParam(value = PAGE, required = false) Integer page,
                                      @RequestParam(value = SIZE, required = false) Integer pageSize,
                                      @RequestParam(value = SORT_BY, required = false) String sortBy,
                                      @RequestParam(value = SORT_ORDER,required = false)String sortOrder,
                                      @RequestParam(value = FILTER_COLUMN_NAME, required = false) String filterColumn,
                                      @RequestParam(value = FILTER_VALUE, required = false) String filterValue);


    @DeleteMapping(DELETE_BY_ID)
    @PreAuthorize(DELETE_OR_ALL_ACCESS)
    ApiResponse<Void> deleteBulkUserById(@RequestParam(ID) String id);
}
