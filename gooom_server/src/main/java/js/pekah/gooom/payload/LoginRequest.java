package js.pekah.gooom.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	@NotBlank
	private String id;

	@NotBlank
	private String password;
}
