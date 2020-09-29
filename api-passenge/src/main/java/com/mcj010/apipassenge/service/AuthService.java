package com.mcj010.apipassenge.service;

import com.mcj010.internalcommon.dto.ResponseResult;

public interface AuthService {
    public ResponseResult auth(String passengerPhone, String code);
}
