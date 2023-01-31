package wandui.dataOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
	
	 	private String userId;
	    private String personId;
	    private String userName;
	    private String password;


	    private String firstName;
	    private String lastName;
	    private String email;
	    private String userType;
	    
	    public String getPassword() {
	        if (password == null) {
	            password = "qa!23456";
	        }
	        return password;
	    }

}
