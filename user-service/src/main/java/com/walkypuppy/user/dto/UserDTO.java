package com.walkypuppy.user.dto;

import com.walkypuppy.user.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserDTO{
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest{
        @NotBlank
        private String name;
        public User toEntity(){
            return User.builder().name(name).build();
        }
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long id;
        private String name;
        public Response(User user){
            id = user.getId();
            name = user.getName();
        }
    }
}
