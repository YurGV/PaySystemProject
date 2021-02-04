package by.grodno.pvt.site.webappsample.dto;

import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserRegistrationDTO {

	@Length(min = 3, max = 20)
	private String firstName;
	
	@Length(min = 3, max = 20)
	private String lastName;
	
	@Email
	private String email;
	
	@Length(min = 5, max = 20)
	private String password;

}
