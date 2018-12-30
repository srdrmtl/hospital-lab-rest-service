package com.nku.hospitalreporting.hospitalreportingservice.payload;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class UniqueIdGenerator {

    private UniqueIdGenerator() {
    }

    public static String generate() {
        String token = Hashing
                .sha1()
                .hashString(UUID.randomUUID().toString() + System.currentTimeMillis(), StandardCharsets.UTF_8)
                .toString();
        return token;
    }
}
