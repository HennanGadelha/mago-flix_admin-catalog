package com.magoflix.admin.catalogo.domain.category;

import com.magoflix.admin.catalogo.domain.AggregateRoot;
import com.magoflix.admin.catalogo.domain.Entity;
import com.magoflix.admin.catalogo.domain.validation.CategoryValidator;
import com.magoflix.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryId> {


    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private  Category(
            final CategoryId anId,
            final String name,
            final String description,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(anId);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean aIsActive){
        final var id = CategoryId.unique();
        final var now = Instant.now();
        final var deletedAt = aIsActive ? null : now;
        return new Category(id, aName, aDescription, aIsActive, now, now, deletedAt);
    }

    public CategoryId getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public void validate(final ValidationHandler handler) {

        new CategoryValidator(this, handler).validate();

    }

    public Category deactivate(){

        final var now = Instant.now();

        if(getDeletedAt() == null ) {
            this.deletedAt = now;
        }
        this.active = false;
        this.updatedAt = now;

        return this;
    }

    public Category activate(){

        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();

        return this;
    }

    public Category update(final String newName,
                           final String newDescription,
                           final boolean isActive){

        if (isActive){
            activate();
        }else {
            deactivate();
        }

        this.name = newName;
        this.description = newDescription;
        this.updatedAt = Instant.now();
        return this;

    }
}
