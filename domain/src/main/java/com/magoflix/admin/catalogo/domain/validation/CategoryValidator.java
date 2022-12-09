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

        if(this.category.getName() == null){
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
