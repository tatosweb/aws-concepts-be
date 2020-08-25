package co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse implements Serializable {

	private String status;
	private String accessToken;
	private String idToken;
	private String refreshToken;
	private String sessionId;
	private Object body;
	private boolean isAdmin;

	public LoginResponse(String status, Object body) {
		this.status = status;
		this.body = body;
	}
}
