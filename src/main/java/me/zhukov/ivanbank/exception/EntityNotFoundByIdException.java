package me.zhukov.ivanbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundByIdException extends BankException {
    private final Object id;

    public EntityNotFoundByIdException(Class<?> clazz, Object id) {
        super("Entity of type [%s] with id [%s] was not found".formatted(clazz.getSimpleName(), id));
        this.id = id;
    }
}
