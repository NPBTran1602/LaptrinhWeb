package bttuan4.model;  // Hoặc bttuan4.model nếu đó là gói đúng

import java.sql.Date;

public class User {  // Viết hoa tên lớp nếu đang viết thường
    private String fullname;  // Trường cho fullname
    private String username;
    private String email;
    private String password;
    private String phone;
    private String avatar;
    private int roleid;
    // Thêm các trường khác nếu cần (ví dụ: createdDate)

    // Getter và Setter cho fullname
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    // Thêm getter/setter cho các trường khác (ví dụ: getPhone, setPhone, getAvatar, v.v.)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    // Constructor(s) nếu cần
    public User() {}

	public void setCreatedDate(Date date) {
		// TODO Auto-generated method stub
		
	}
}