package io.github.junrdev.booker.domain.enumarations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public enum PAYMENT_STATUS {

    COMPLETED, PENDING, PROCESSING, ON_SITE;

    private static final Logger LOGGER = LoggerFactory.getLogger(PAYMENT_STATUS.class);

    PAYMENT_STATUS getStatus(String statusStr){
        LOGGER.debug("values {}", (Object) PAYMENT_STATUS.values());
        return switch (statusStr.toUpperCase(Locale.ROOT)){
            case "COMPLETED" -> COMPLETED;
            case "PENDING" -> PENDING;
            case "PROCESSING" -> PROCESSING;
            case "ON_SITE" -> ON_SITE;
            default -> PENDING;
        };
    }
}
