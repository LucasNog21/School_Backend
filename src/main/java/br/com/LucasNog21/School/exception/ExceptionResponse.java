package br.com.LucasNog21.School.exception;

import java.util.Date;

public record ExceptionResponse(Date timeStamp, String message, String details) {}