package gt.edu.umg.payload;

import java.util.List;

public class ApiResponse {
    
    private String status;
    private boolean success;
    private String message;
    private List<?> data;
    private String singleValue;
    private AuthResponse authResponse;

    public ApiResponse(){

    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(String singleValue) {
        this.singleValue = singleValue;
    }

	public void setAuth(AuthResponse authResponse) {
        this.authResponse=authResponse;
    }
    public AuthResponse getAuth(){
        return authResponse;
    }

   
}
