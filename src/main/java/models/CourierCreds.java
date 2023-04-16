package models;

public class CourierCreds {
    String login;
    String password;


    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }

        public static CourierCreds getCredentials(Courier courier){
        return new CourierCreds(courier.getLogin(),courier.getPassword());
    }
}