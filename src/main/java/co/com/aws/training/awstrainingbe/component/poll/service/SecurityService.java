package co.com.aws.training.awstrainingbe.component.poll.service;


import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.LoginResponse;

public interface SecurityService {
	
	 LoginResponse getToken(String username, String password);
	 LoginResponse signOut(String token);
	 LoginResponse refreshToken(String token);
}
