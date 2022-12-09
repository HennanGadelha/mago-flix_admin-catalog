package com.magoflix.admin.catalogo.domain.category;

import com.magoflix.admin.catalogo.domain.AggregateRoot;

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
        return new Category(id, aName, aDescription, aIsActive, now, now, null);
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
}
