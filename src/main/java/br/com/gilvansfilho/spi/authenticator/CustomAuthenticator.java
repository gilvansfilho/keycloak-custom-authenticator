package br.com.gilvansfilho.spi.authenticator;

import java.util.Random;

import jakarta.ws.rs.core.Response;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class CustomAuthenticator implements Authenticator {

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        Integer num = new Random().nextInt(11);
        System.out.println("Generated lucky number: " + num);
        if(!even(num)) {
            String unluckyMsg = context.getAuthenticatorConfig().getConfig().get(CustomAuthenticatorFactory.PROPERTY_NAME);
            context.failure(AuthenticationFlowError.INVALID_USER, context.form().setError(unluckyMsg).createErrorPage(Response.Status.UNAUTHORIZED));
            return;

        }
        context.success();
    }

    public boolean even(Integer num) {
        return num % 2 == 0;
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {

    }

    @Override
    public void close() {

    }

}