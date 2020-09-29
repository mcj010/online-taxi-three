package com.mcj010.servicepassengeruser.service;


import com.mcj010.internalcommon.dto.ResponseResult;

public interface PassengerUserService {

    public ResponseResult login(String passengerPhone);

    public ResponseResult logout(String token);
}
