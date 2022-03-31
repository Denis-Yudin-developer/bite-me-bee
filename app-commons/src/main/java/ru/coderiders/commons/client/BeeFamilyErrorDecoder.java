package ru.coderiders.commons.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

public class BeeFamilyErrorDecoder implements ErrorDecoder {
    private static final String BEE_FAMILY_NOT_FOUND = "Пчелиная семья с id=%s не найдена";
    private static final String HIVE_IS_OCCUPIED = "Улей занят другой пчелиной семьёй";
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
            return new NotFoundException(BEE_FAMILY_NOT_FOUND);
        } else if (response.status() == 400) {
            return new BadRequestException(HIVE_IS_OCCUPIED);
        }
        return errorDecoder.decode(s, response);
    }
}
