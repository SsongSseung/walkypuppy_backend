package com.walkypuppy.user.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class KakaoUserInfo {
    private long id;
    private boolean has_signed_up;
    private LocalDateTime connected_at;
    private LocalDateTime synced_at;
    private JsonNode properties; // Assuming you use Jackson's JsonNode
    private KakaoAccount kakao_account;
    private Partner for_partner;
}
