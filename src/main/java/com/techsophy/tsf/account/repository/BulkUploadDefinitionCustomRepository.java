package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.BulkUserDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BulkUploadDefinitionCustomRepository
{
    List<BulkUserDefinition> findBulkUsersByFilter(String filterColumn, String filterValue);
    List<BulkUserDefinition> findBulkUsersByFilterAndSort(String filterColumn, String filterValue,String sortBy,String sortOrder);
    Page<BulkUserDefinition> findBulkUserByFilterPageable(String filterColumn, String filterValue,Pageable pageable);
    Page<BulkUserDefinition> findBulkUserByFilterSortPageable(String filterColumn, String filterValue,String sortBy,String sortOrder,Pageable pageable);
    List<BulkUserDefinition> findBulkUsersByQ(String q);
    List<BulkUserDefinition> findBulkUsersByQSort(String q,String sortBy,String sortOrder);
    Page<BulkUserDefinition> findBulkUsersByQPageable(String q,Pageable pageable);
    Page<BulkUserDefinition> findBulkUsersByQSortPageable(String q,String sortBy,String sortOrder,Pageable pageable);
}
