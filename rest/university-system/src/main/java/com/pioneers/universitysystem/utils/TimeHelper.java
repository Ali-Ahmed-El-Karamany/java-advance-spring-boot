package com.pioneers.universitysystem.utils;

import java.sql.Timestamp;
import java.time.Instant;

public final class TimeHelper {

    private TimeHelper() {
        throw new AssertionError("Utility Class");
    }

    public static Timestamp currentTimeStamp() {
        return Timestamp.from(Instant.now());
    }
}
