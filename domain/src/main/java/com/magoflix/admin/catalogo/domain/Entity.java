package com.magoflix.admin.catalogo.domain;

import com.magoflix.admin.catalogo.domain.validation.ValidationHandler;

import java.util.Objects;

public abstract  class Entity<ID extends Identifier> {

    protected final ID id;

    public Entity(final ID id) {
        Objects.requireNonNull(id, "'id' should not be null");
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public abstract  void validate(ValidationHandler handler);
}
