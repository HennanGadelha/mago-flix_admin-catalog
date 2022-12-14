package com.magoflix.admin.catalogo.domain;

import com.magoflix.admin.catalogo.domain.validation.ValidationHandler;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    public AggregateRoot(ID id) {
        super(id);
    }

}
