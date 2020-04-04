package kg.bish.courier;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ALTYNBEK on 05.02.2020.
 */
public class Config {
   /* private String advPath = "C:"+File.separator+"path"+File.separator+"adv";//+File.separator+maxCount+File.separator;
    private String passportPath = "C:"+File.separator+"path"+File.separator+"passport";//+File.separator+maxCount+File.separator;
    private String courierPath = "C:"+File.separator+"path"+File.separator+"courier";//+File.separator+maxCount+File.separator;
*/
    private Connection connection;

    private String advPath = "D:"+File.separator+"path"+File.separator+"adv";//+File.separator+maxCount+File.separator;
    //private String passportPath = "D:"+File.separator+"path"+File.separator+"passport";//+File.separator+maxCount+File.separator;
    private String courierPath = "D:"+File.separator+"path"+File.separator+"courier";//+File.separator+maxCount+File.separator;


    public String getAdvPath() {
        return advPath;
    }

    public void setAdvPath(String advPath) {
        this.advPath = advPath;
    }

    public String getCourierPath() {
        return courierPath;
    }

    public void setCourierPath(String courierPath) {
        this.courierPath = courierPath;
    }

    public Connection getConnection() throws URISyntaxException {
        String username=null;
        String password=null;
        String dbUrl=null;
        try{
//            URI dbUri = new URI(System.getenv("DATABASE_URL"));
//            username = dbUri.getUserInfo().split(":")[0];
//            password = dbUri.getUserInfo().split(":")[1];
//            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();



            connection = DriverManager.getConnection(dbUrl, username, password);
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cosmic", "postgres", "postgres");
        }catch (Exception e){}

        return connection;
    }
}
