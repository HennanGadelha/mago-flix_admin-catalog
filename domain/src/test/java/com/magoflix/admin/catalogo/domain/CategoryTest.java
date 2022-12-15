package com.magoflix.admin.catalogo.domain;

import com.magoflix.admin.catalogo.domain.category.Category;
import com.magoflix.admin.catalogo.domain.exception.DomainException;
import com.magoflix.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
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


}
