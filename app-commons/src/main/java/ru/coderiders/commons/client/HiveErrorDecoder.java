package ru.coderiders.commons.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

public class HiveErrorDecoder implements ErrorDecoder {
    private static final String HIVE_NOT_FOUND = "Улей с id=%s не найден";
    private static final String HIVE_ALREADY_EXISTS = "Улей с таким названием уже существует";
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
            return new NotFoundException(HIVE_NOT_FOUND);
        } else if (response.status() == 400) {
            return new BadRequestException(HIVE_ALREADY_EXISTS);
        }
        return errorDecoder.decode(s, response);
    }
}
