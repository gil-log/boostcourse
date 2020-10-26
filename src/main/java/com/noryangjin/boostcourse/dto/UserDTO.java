package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

public class UserDTO {

    @Getter
    @Setter
    public static class LoginInfo{

        private long id;
        private String name;
        private String password;
        private String email;
        private String phone;
        private LocalDateTime create_date;
        private LocalDateTime modify_date;

        public LoginInfo(User user){
            this.id = user.getId();
            this.name = user.getName();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.create_date = user.getCreate_date();
            this.modify_date = user.getModify_date();
        }
    }
}
