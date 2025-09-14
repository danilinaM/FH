package org.example.builders;

import org.example.model.User;

import java.util.Map;

public class UserBuilder extends User {
    
    public UserBuilder(Map<String, String> map) {
        super();
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                switch (key) {
                    case "id":
                        setId(Integer.valueOf(value));
                        break;
                    case "gcid":
                        setGcid(Integer.valueOf(value));
                        break;
                    case "name":
                        setName(value);
                        break;
                    case "email":
                        setEmail(value);
                        break;
                    case "email_verified_at":
                        setEmailVerifiedAt(value);
                        break;
                    case "phone":
                        setPhone(value);
                        break;
                    case "phone_verified_at":
                        setPhoneVerifiedAt(value);
                        break;
                    case "created_at":
                        setCreatedAt(value);
                        break;
                    case "initials":
                        setInitials(value);
                        break;
                }
            }
        }
    }
    @Override
    public String toString() { return new com.google.gson.Gson().toJson(this);}
}
