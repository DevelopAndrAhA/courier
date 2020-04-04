package kg.bish.courier.models_to_serialize;

/**
 * Created by ALTYNBEK on 05.02.2020.
 */
public class CourierJSON {
    private long id;
    private String FIO;
    private String phone;
    private String secretKey;
    private String login;
    private String password;
    private String photo;
    private String photo_p1;
    private String photo_p2;
    private String statusStr;



    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getPhoto_p1() {
        return photo_p1;
    }

    public void setPhoto_p1(String photo_p1) {
        this.photo_p1 = photo_p1;
    }

    public String getPhoto_p2() {
        return photo_p2;
    }

    public void setPhoto_p2(String photo_p2) {
        this.photo_p2 = photo_p2;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
