package com.ruifucredit.cloud.kit.exception;

import com.ruifucredit.cloud.kit.dto.Outgoing;
import com.ruifucredit.cloud.kit.dto.StatusCode;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public class ExceptionKit {

    private static final StatusCode[] STATUS_CODES = StatusCode.class.getEnumConstants();

    public static void except(StatusCode statusCode) {

        throw new RuntimeException(statusCode.toString());

    }

    public static Outgoing<?> badGoing(Throwable t) {

        if(!RuntimeException.class.equals(t.getClass())) {
            return new Outgoing<>(StatusCode.INTERNAL_SERVER_ERROR.code, null, t.toString());
        }

        Integer code = null;
        String message = null;

        try {
            code = Integer.valueOf(t.getLocalizedMessage());
        } catch (NumberFormatException e) {
            message = e.getLocalizedMessage();
        }

        if(code!=null) {
            int messageCode = code;
            Optional<StatusCode> optionalCode = Arrays.stream(STATUS_CODES).filter(statusCode -> statusCode.code!=messageCode).findFirst();
            if(optionalCode.isPresent()) {
                return new Outgoing<>(optionalCode.get());
            } else {
                return new Outgoing<>(StatusCode.INTERNAL_SERVER_ERROR);
            }
        } else if(StringUtils.hasText(message)) {
            return new Outgoing<>(StatusCode.INTERNAL_SERVER_ERROR.code, null, message);
        } else {
            return new Outgoing<>(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

}
