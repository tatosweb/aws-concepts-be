package co.com.aws.training.awstrainingbe.component.poll.service;

import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.LoginResponse;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.JwtDsl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityServiceImpl implements SecurityService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    //@Value("${clientId}")
    private String cognitoClienteId = "2skvh18g4j1qo9uugssackk2bk";

    //@Value("${userPoolId}")
    private String cognitoPoolId = "us-east-1_aPV4ri9GE";

    @Autowired
    private AWSCognitoIdentityProviderClient cognitoClient;

    @Override
    public LoginResponse getToken(String username, String password) {
        LoginResponse result = new LoginResponse();
        result.setStatus("ERROR");
        result.setBody("No se pudo autenticar");

        Map<String, String> authParams = new HashMap<String, String>();
        authParams.put("USERNAME", username);
        authParams.put("PASSWORD", password);

        try {
            AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                    .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                    .withAuthParameters(authParams)
                    .withClientId(cognitoClienteId)
                    .withUserPoolId(cognitoPoolId);

            AdminInitiateAuthResult authResponse = cognitoClient.adminInitiateAuth(authRequest);

            isAdmin(authResponse.getAuthenticationResult().getAccessToken());

            if (authResponse.getChallengeName() == null || authResponse.getChallengeName().isEmpty()) {
                authResponse.getAuthenticationResult().getAccessToken();
                result.setStatus("OK");
                result.setAccessToken(authResponse.getAuthenticationResult().getAccessToken());
                result.setIdToken(authResponse.getAuthenticationResult().getIdToken());
                result.setRefreshToken(authResponse.getAuthenticationResult().getRefreshToken());
                result.setBody("Autenticacion correcta");
                result.setAdmin(isAdmin(authResponse.getAuthenticationResult().getAccessToken()));
            } else if (ChallengeNameType.NEW_PASSWORD_REQUIRED.name().equals(authResponse.getChallengeName())) {
                result.setStatus("OK-UPDATE");
                result.setBody("nuevo password requerido");
                result.setSessionId(authResponse.getSession());
            }
        } catch (NotAuthorizedException e) {
            result.setBody("Usuario no autorizado");
        } catch (UserNotFoundException e) {
            result.setBody("Usuario no autorizado");
        } catch (PasswordResetRequiredException e) {
            result.setBody("Reinicie su password");
            result.setStatus("OK-RESET");
        }

        return result;
    }

    /**
     * Se puede ingresar el accessToken o idToken
     */
    @Override
    public LoginResponse signOut(String token) {
        LoginResponse result = new LoginResponse();
        result.setStatus("ERROR");
        result.setBody("No se pudo autenticar");

        try {
            GlobalSignOutRequest rq = new GlobalSignOutRequest()
                    .withAccessToken(token);
            cognitoClient.globalSignOut(rq);
            result.setStatus("OK");
            result.setBody("SignOut correcto");
        } catch (Exception e) {
            logger.error("[signOut] Ocurrio un error inesperado: ", e);
            result.setBody(e.getMessage());
        }

        return result;
    }

    /**
     * Se necesita el refreshToken como input
     */
    @Override
    public LoginResponse refreshToken(String token) {
        LoginResponse result = new LoginResponse();
        result.setStatus("ERROR");
        result.setBody("No se pudo autenticar");

        Map<String, String> authParams = new HashMap<String, String>();
        authParams.put("REFRESH_TOKEN", token);

        try {
            InitiateAuthRequest authRequest = new InitiateAuthRequest()
                    .withAuthFlow(AuthFlowType.REFRESH_TOKEN_AUTH)
                    .withAuthParameters(authParams)
                    .withClientId(cognitoClienteId);

            InitiateAuthResult authResponse = cognitoClient.initiateAuth(authRequest);



            if (authResponse.getChallengeName() == null || authResponse.getChallengeName().isEmpty()) {
                authResponse.getAuthenticationResult().getAccessToken();
                result.setStatus("OK");
                result.setAccessToken(authResponse.getAuthenticationResult().getAccessToken());
                result.setIdToken(authResponse.getAuthenticationResult().getIdToken());
                result.setBody("actualizacion token correcta");
            }
        } catch (NotAuthorizedException e) {
            result.setBody("Usuario no autorizado");
        } catch (UserNotFoundException e) {
            result.setBody("Usuario no autorizado");
        } catch (PasswordResetRequiredException e) {
            result.setBody("Reinicie su password");
            result.setStatus("OK-RESET");
        }

        return result;
    }

    private boolean isAdmin(String token){
        Base64 base64 = new Base64();
        DecodedJWT jwt = JWT.decode(token);
        String decodedString = new String(base64.decode(jwt.getPayload().getBytes()));
        return decodedString.contains("ADMIN") ? true : false;
    }

}
