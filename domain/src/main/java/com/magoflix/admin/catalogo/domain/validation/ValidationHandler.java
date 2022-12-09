package com.magoflix.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);

    ValidationHandler append(ValidationHandler validationHandler);

    ValidationHandler validate(Validation validationHandler);

    List<Error> getErrors();

    default boolean hasError(){
        return getErrors() != null && !getErrors().isEmpty();
    }


    interface Validation {
        void validate();
    }

}
