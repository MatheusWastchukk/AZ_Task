package br.com.az.leilao.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class SafeTextValidator implements ConstraintValidator<SafeText, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String normalized;

        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        normalized = value.toLowerCase(Locale.ROOT);

        return !normalized.contains("<")
                && !normalized.contains(">")
                && !normalized.contains("javascript:")
                && !normalized.contains("onerror=")
                && !normalized.contains("onload=")
                && !normalized.contains("<script");
    }
}
