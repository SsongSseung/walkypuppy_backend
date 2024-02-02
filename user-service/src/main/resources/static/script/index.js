const listReq = () => {
    location.href = "/board/";
}

function kakaoLogin() {
    // Define Kakao OAuth parameters
    var clientId = "97398d84a19635a676adb1fa832bda91";
    var redirectUri = "http://127.0.0.1:8080/auth/kakao/callback";
    var responseType = "code";

    // Construct the Kakao OAuth URL with variables
    var kakaoOAuthUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=${responseType}`;

    // Redirect to the Kakao OAuth URL
    window.location.href = kakaoOAuthUrl;
}