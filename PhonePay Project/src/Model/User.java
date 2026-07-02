package Model;

public class User {
    private String userId;
    private String name;
    private String mobile;
    private String upiId;

    public User(String userId, String name, String mobile, String upiId) {
        this.userId = userId;
        this.name = name;
        this.mobile = mobile;
        this.upiId = upiId;
    }

    //Get Method
    public String getUserId() {
        return userId; }
    public String getName() {
        return name; }
    public String getMobile() {
        return mobile; }
    public String getUpiId() {
        return upiId; }

    //Set Method
    public void setName(String name) {
        this.name = name; }
    public void setMobile(String mobile) {
        this.mobile = mobile; }
    public void setUpiId(String upiId) {
        this.upiId = upiId; }

    @Override
    public String toString() {
        return userId + "," + name + "," + mobile + "," + upiId;
    }

    public static User fromString(String data) {
        String[] d = data.split(",");
        return new User(d[0], d[1], d[2], d[3]);
    }
}