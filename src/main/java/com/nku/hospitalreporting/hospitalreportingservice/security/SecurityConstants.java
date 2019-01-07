package com.nku.hospitalreporting.hospitalreportingservice.security;

/**
 *
 * @author serdar
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 423_000_000; // 5 gün
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String DOWNLOAD_URL = "/downloadFile/{fileid}/{fileName:.+}";
}
