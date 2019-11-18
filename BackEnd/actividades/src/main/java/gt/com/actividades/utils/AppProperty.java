package gt.com.actividades.utils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperty {

    private String jwtPassword;
    private int jwtExpiration;
    private int showErrors;


    public String getJwtPassword() {
        return this.jwtPassword;
    }

    public void setJwtPassword(String jwtPassword) {
        this.jwtPassword = jwtPassword;
    }

    public int getJwtExpiration() {
        return this.jwtExpiration;
    }

    public void setJwtExpiration(int jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public int getShowErrors() {
        return this.showErrors;
    }

    public void setShowErrors(int showErrors) {
        this.showErrors = showErrors;
    }

}