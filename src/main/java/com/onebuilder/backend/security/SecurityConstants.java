/*package com.onebuilder.backend.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static final String SECRET = "$ecr3tK3yT0K3nJWT#"; // Llave Secreta para generar los tokens
    public static final int EXPIRATION_ONE_DAY = 1; // Expiracion para un dia
    public static final String TOKEN_PREFIX = "WO "; // Prefijo del header Authorization
    public static final String HEADER_STRING = "Authorization"; // Nombre del header para el token JWT
    public static final String SIGN_UP_URL = "/login"; // Url del servicio de login
    public static final String LOG_OUT_URL = "/logout"; // Url del servicio de logout
    private static final String REGEX = ","; // Expresion para separar los datos del properties
    public static final String TENANT_KEY = "tenantId";
    public static final String SCHEMA_KEY = "schema";
    public static final String FECHA_KEY = "fecha";

    @Value("${cors.allow.credentials}")
    public boolean cors_allow_credentials;

    @Value("${cors.allowed.origins}")
    public String cors_allowed_origin;

    @Value("${cors.allowed.headers}")
    public String cors_allowed_headers;

    @Value("${cors.allowed.methods}")
    public String cors_allowed_methods;

    @Value("${cors.exposed.headers}")
    public String cors_exposed_headers;

    @Value("${cors.maxage}")
    public Long cors_max_age;

    public boolean isCors_allow_credentials() {
        return cors_allow_credentials;
    }

    public List<String> getCors_allowed_origin() {
        return Arrays.asList(cors_allowed_origin.split(REGEX));
    }

    public List<String> getCors_allowed_headers() {
        return Arrays.asList(cors_allowed_headers.split(REGEX));
    }

    public List<String> getCors_allowed_methods() {
        return Arrays.asList(cors_allowed_methods.split(REGEX));
    }

    public List<String> getCors_exposed_headers() {
        return Arrays.asList(cors_exposed_headers.split(REGEX));
    }

    public Long getCors_max_age() {
        return cors_max_age;
    }

}
*/