package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.PaginationResponsePayload;
import com.techsophy.tsf.account.dto.ThemesResponse;
import com.techsophy.tsf.account.dto.ThemesResponseSchema;
import com.techsophy.tsf.account.dto.ThemesSchema;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.stream.Stream;

public interface ThemesService
{
    ThemesResponse saveThemesData(ThemesSchema themesSchema) throws JsonProcessingException;

    ThemesResponseSchema getThemesDataById(String id);

    Stream<ThemesResponseSchema> getAllThemesData(String deploymentIdList, String q, Sort sort);

    PaginationResponsePayload getAllThemesData(String q, Pageable pageable);

    void deleteThemesDataById(String id);

    ResponseEntity<Resource> downloadTheme(String id) throws IOException;

    ThemesResponse uploadTheme(MultipartFile file,String name) throws IOException;
}
