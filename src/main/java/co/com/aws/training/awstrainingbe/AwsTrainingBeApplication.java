package co.com.aws.training.awstrainingbe;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class})
public class AwsTrainingBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsTrainingBeApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public AWSCognitoIdentityProviderClient CognitoClient() {
		AWSCognitoIdentityProviderClient cognitoClient = new AWSCognitoIdentityProviderClient(new DefaultAWSCredentialsProviderChain());
		//cognitoClient.setRegion(Region.getRegion(Regions.fromName(System.getenv("AWS_COGNITO_REGION"))));
		cognitoClient.setRegion(Region.getRegion(Regions.US_EAST_1));

		return cognitoClient;
	}



}
