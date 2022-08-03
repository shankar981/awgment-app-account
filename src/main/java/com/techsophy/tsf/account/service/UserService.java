package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.PaginationResponsePayload;
import com.techsophy.tsf.account.dto.AuditableData;
import com.techsophy.tsf.account.dto.UserData;
import com.techsophy.tsf.account.entity.UserDefinition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserService
{
    @Transactional(rollbackFor = Exception.class)
    UserDefinition saveUser(UserData userSchema) throws JsonProcessingException;

    AuditableData getUserById(String id) throws IOException, AccountNotFoundException;

    List<UserData> getAllUsers(String q, Sort sort);

    PaginationResponsePayload getAllUsers(String q,Pageable pageable);

    @Transactional(rollbackFor = Exception.class)
    void deleteUserById(String id);

    UserData getUserByLoginId(String loginId);
}
