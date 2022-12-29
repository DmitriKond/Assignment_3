public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private Integer userType;

    public static final int USER_TYPE_USER = 1;
    public static final int USER_TYPE_BROKER = 2;

    public User (String userName, String password, String phoneNumber, Integer userType) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public boolean userNameEquals (String otherUserName) {
        boolean valid = false;
        if (this.userName.equalsIgnoreCase(otherUserName)) {
            valid = true;
        }
        return valid;
    }

    public String getUserName () {
        return this.userName;
    }

    public String getPassword () {
        return this.password;
    }

    public int getUserType () {
        return this.userType;
    }

    public String getPrintableUserType () {
        String output = "";
        switch (this.userType) {
            case USER_TYPE_USER -> output = "Regular User";
            case USER_TYPE_BROKER -> output = "Real Estate Broker";
        }
        return output;
    }

    public String toString () {
        return this.userName + " " + this.phoneNumber + " (" + getPrintableUserType() + ")";
    }

}
