package com.magoflix.admin.catalogo.domain.validation.handler;

import com.magoflix.admin.catalogo.domain.exception.DomainException;
import com.magoflix.admin.catalogo.domain.validation.Error;
import com.magoflix.admin.catalogo.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(Error error) {
       throw  DomainException.with(error);
    }

    @Override
    public ValidationHandler append(ValidationHandler validationHandler) {
        throw DomainException.with(validationHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation validationHandler) {
        try {
            validationHandler.validate();
        }catch (final Exception ex){
            throw DomainException.with(new Error(ex.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
