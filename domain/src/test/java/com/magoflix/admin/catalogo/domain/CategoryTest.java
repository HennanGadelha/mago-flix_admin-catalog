package com.magoflix.admin.catalogo.domain;

import com.magoflix.admin.catalogo.domain.category.Category;
import com.magoflix.admin.catalogo.domain.exception.DomainException;
import com.magoflix.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

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

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = null;
        final var messageError = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedDescription = "category series";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(messageError, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final var blankName = " ";
        final var messageError = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedDescription = "category series";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(blankName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(messageError, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final var blankName = "ab ";
        final var messageError = "'name' must be between 3 and 255 character";
        final var expectedErrorCount = 1;
        final var expectedDescription = "category series";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(blankName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(messageError, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final var blankName = """
                O incentivo ao avanço tecnológico, assim como a consulta aos diversos militantes representa uma abertura 
                para a melhria dos métodos utilizados na avaliação de resultado O incentivo ao avanço tecnológico, assim
                como a consulta aos diversos militantes representa uma abertura para a melhoria dos métodos utilizados 
                na avaliação de resultados
                """;
        final var messageError = "'name' must be between 3 and 255 character";
        final var expectedErrorCount = 1;
        final var expectedDescription = "category series";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(blankName, expectedDescription, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(messageError, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = "series";
        final var messageError = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedDescription = " ";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualCategorty =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategorty);
        Assertions.assertNotNull(actualCategorty.getId());
        Assertions.assertEquals(expectedName, actualCategorty.getName());
        Assertions.assertEquals(expectedDescription, actualCategorty.getDescription());
        Assertions.assertNotNull(actualCategorty.getCreatedAt());
        Assertions.assertNotNull(actualCategorty.getUpdatedAt());
        Assertions.assertNull(actualCategorty.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = "series";
        final var messageError = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedDescription = "category more";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualCategorty =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategorty);
        Assertions.assertNotNull(actualCategorty.getId());
        Assertions.assertEquals(expectedName, actualCategorty.getName());
        Assertions.assertEquals(expectedDescription, actualCategorty.getDescription());
        Assertions.assertNotNull(actualCategorty.getCreatedAt());
        Assertions.assertNotNull(actualCategorty.getUpdatedAt());
        Assertions.assertNull(actualCategorty.getDeletedAt());
    }

    @Test
    public void givenAValidateActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated(){

        final var expectedName = "SERIES";
        final var expectedDescription = "category series";
        final var expectedIsActive = false;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var  actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());

    }

    @Test
    public void givenAValidateInactiveCategory_whenCallActivate_thenReturnCategoryActivated(){

        final var expectedName = "SERIES";
        final var expectedDescription = "category series";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var  actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());

    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated(){

        final var oldName = "MOVIES";
        final var oldDescription = "category MOVIES";
        final var expectedIsActive = true;

        final var newName = "SERIES";
        final var newDescription = "category series";

        final var aCategory =
                Category.newCategory(oldName, oldDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var categoryUpdated = aCategory.update(newName, newDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> categoryUpdated.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), categoryUpdated.getId());
        Assertions.assertEquals(newName, categoryUpdated.getName());
        Assertions.assertEquals(newDescription, categoryUpdated.getDescription());
        Assertions.assertNotNull(categoryUpdated.getCreatedAt());
        Assertions.assertEquals(expectedIsActive, categoryUpdated.isActive());
        Assertions.assertTrue(categoryUpdated.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertEquals(createdAt, categoryUpdated.getCreatedAt());
        Assertions.assertNull(categoryUpdated.getDeletedAt());

    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated(){

        final var oldName = "MOVIES";
        final var oldDescription = "category MOVIES";
        final var expectedIsActive = false;

        final var newName = "SERIES";
        final var newDescription = "category series";

        final var aCategory =
                Category.newCategory(oldName, oldDescription, true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var categoryUpdated = aCategory.update(newName, newDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> categoryUpdated.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(aCategory.getId(), categoryUpdated.getId());
        Assertions.assertEquals(newName, categoryUpdated.getName());
        Assertions.assertEquals(newDescription, categoryUpdated.getDescription());
        Assertions.assertFalse(categoryUpdated.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());
        Assertions.assertTrue(categoryUpdated.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertEquals(createdAt, categoryUpdated.getCreatedAt());

    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated(){

        final var oldName = "MOVIES";
        final var oldDescription = "category MOVIES";
        final var expectedIsActive = true;

        final String newName = null;
        final var newDescription = "category series";

        final var aCategory =
                Category.newCategory(oldName, oldDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var categoryUpdated = aCategory.update(newName, newDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), categoryUpdated.getId());
        Assertions.assertEquals(newName, categoryUpdated.getName());
        Assertions.assertEquals(newDescription, categoryUpdated.getDescription());
        Assertions.assertTrue(categoryUpdated.isActive());
        Assertions.assertTrue(categoryUpdated.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertEquals(createdAt, categoryUpdated.getCreatedAt());
        Assertions.assertNull(aCategory.getDeletedAt());
        
    }


}
