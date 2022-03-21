package com.example.semesterprojektbackend.model.enumuration.enumValidation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import javax.validation.ClockProvider;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class ValueOfEnumValidatorTest {
    @Test
    void testIsValid() {
        ValueOfEnumValidator valueOfEnumValidator = new ValueOfEnumValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(valueOfEnumValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

