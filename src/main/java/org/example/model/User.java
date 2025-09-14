package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private Integer gcid;
    private String name;
    private String email;
    
    @SerializedName("email_verified_at")
    private String emailVerifiedAt;
    
    private String phone;
    
    @SerializedName("phone_verified_at")
    private String phoneVerifiedAt;
    
    @SerializedName("created_at")
    private String createdAt;
    
    private String initials;

    public User(Map<String, String> map) {
    }
}