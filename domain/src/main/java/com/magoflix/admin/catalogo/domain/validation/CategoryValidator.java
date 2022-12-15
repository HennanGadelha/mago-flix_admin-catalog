package com.magoflix.admin.catalogo.domain.validation;

import com.magoflix.admin.catalogo.domain.category.Category;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        this.checkNameConstraints();
    }

    private void checkNameConstraints(){

        final var name = this.category.getName();

        if(name == null){
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if(name.isBlank()){
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var nameLength = name.trim().length();
        if(nameLength> 255 || nameLength < 3){
            this.validationHandler().append(new Error("'name' must be between 3 and 255 character"));
        }
    }
}
