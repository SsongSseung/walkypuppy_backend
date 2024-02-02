package com.walkypuppy.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class KakaoLoginTestController {
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {

        System.out.println("kakao oauth success! code: " + code);

        // post 방식으로 key=value 데이터를 요청 (카카오톡으로)
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "97398d84a19635a676adb1fa832bda91");
        params.add("redirect_uri", "http://127.0.0.1:8080/auth/kakao/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        System.out.println("kakao token request success! response" + response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Access token: " + oauthToken.getAccess_token());

        // post 방식으로 key=value 데이터를 요청 (카카오톡으로)
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kauth.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoProfileRequest,
                String.class
        );

//        KakaoUserInfo kakaoUserInfo;
//        try {
//           kakaoUserInfo  = objectMapper.readValue(response2.getBody(), KakaoUserInfo.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

//        System.out.println("KakaoUserInfo:\n n" + kakaoUserInfo.toString());

        System.out.println("kakao profile request success! response: " + response2.getBody());
        return "kakao profile request success! response: " + response2.getBody();
    }
}
