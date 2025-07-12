package com.twogether.deokhugam.common.exception;

import java.time.Instant;
import java.util.Map;
import lombok.Getter;

@Getter
public class DeokhugamException extends RuntimeException {

    private final Instant timestamp;
    private final ErrorCode errorCode;
    private final Map<String, Object> details;

    /**
     * 기본 생성자
     */
    public DeokhugamException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = Map.of();
    }

    /**
     * detail 값과 함께 생성
     */
    public DeokhugamException(ErrorCode errorCode, Map<String, Object> details){
        super(errorCode.getMessage());
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = details != null ? Map.copyOf(details) : Map.of();
    }

    /**
     * 예외와 함께 생성
     */
    public DeokhugamException(ErrorCode errorCode, Throwable cause){
        super(errorCode.getMessage(), cause);
        this.timestamp = Instant.now();
        this.errorCode = errorCode;
        this.details = Map.of();
    }

}
