package com.mcj010.servicepassengeruser.controller;
import com.mcj010.internalcommon.dto.ResponseResult;
import com.mcj010.internalcommon.dto.servicepassengeruser.request.LoginRequest;
import com.mcj010.servicepassengeruser.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限控制类
 * 此类对用户的登录、权限和登出进行控制。
 *
 *
 *
 * @author 马士兵教育:zhangyiming
 * @author 马士兵教育:chaopengfei
 *
 * @date 2020/06/06
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PassengerUserService passengerUserService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginRequest request){

        String passengerPhone = request.getPassengerPhone();
        return passengerUserService.login(passengerPhone);
    }

    /**
     *
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    public ResponseResult logout(String token) throws Exception{
        passengerUserService.logout(token);
        return ResponseResult.success("");
    }
}
