package chatapp;


public class Information {
    String userName;
    Connection connection;

    public Information(String userName,Connection connection){
        this.userName = userName;
        this.connection = connection;
    }

    public Connection getConnection(){
        return connection;
    }

}
