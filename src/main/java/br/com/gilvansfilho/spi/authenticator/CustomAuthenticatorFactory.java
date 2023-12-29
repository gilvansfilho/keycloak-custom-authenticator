package br.com.gilvansfilho.spi.authenticator;

import java.util.ArrayList;
import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

public class CustomAuthenticatorFactory implements AuthenticatorFactory {

    private static final CustomAuthenticator AUTHENTICATOR = new CustomAuthenticator();
    
    public static final String PROVIDER_ID = "custom-authenticator";
    private static final String HELP_TEXT = "Custom authenticator which does something amazing";
    private static final String DISPLAY_TYPE = "Custom Authenticator";

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
        AuthenticationExecutionModel.Requirement.REQUIRED,
        AuthenticationExecutionModel.Requirement.ALTERNATIVE,
        AuthenticationExecutionModel.Requirement.DISABLED
    };

    private final List<ProviderConfigProperty> configProperties = new ArrayList<>();

    /* Configs Properties*/
    public static final String PROPERTY_NAME = "custom.property.msg";
    private static final String PROPERTY_LABEL = "Custom Property (msg)";
    private static final String PROPERTY_HELP_TEXT = "My Custom Property";
    private static final String PROPERTY_DEFAULT_VALUE = "You're out of luck. Try again!";

    @Override
    public Authenticator create(KeycloakSession session) {
        return AUTHENTICATOR;
    }

    @Override
    public void init(Scope config) {
        ProviderConfigProperty property;
        
        property = new ProviderConfigProperty();
        property.setName(PROPERTY_NAME);
        property.setLabel(PROPERTY_LABEL);
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText(PROPERTY_HELP_TEXT);
        property.setDefaultValue(PROPERTY_DEFAULT_VALUE);
        configProperties.add(property);

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return DISPLAY_TYPE;
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

}