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
public class LoginDto  implements Serializable {

	private String username;
	private String password;
}
