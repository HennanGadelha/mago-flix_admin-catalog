package com.magoflix.admin.catalogo.domain;

import com.magoflix.admin.catalogo.domain.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Series";
        final var expectedDescription = "category series";
        final var expectedIsActive = true;

        final var actualCategorty =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategorty);
        Assertions.assertNotNull(actualCategorty.getId());
        Assertions.assertEquals(expectedName, actualCategorty.getName());
        Assertions.assertEquals(expectedDescription, actualCategorty.getDescription());
        Assertions.assertNotNull(actualCategorty.getCreatedAt());
        Assertions.assertNotNull(actualCategorty.getUpdatedAt());
        Assertions.assertNull(actualCategorty.getDeletedAt());
    }

}
