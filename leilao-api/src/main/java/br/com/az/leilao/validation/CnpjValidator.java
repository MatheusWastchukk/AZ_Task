package br.com.az.leilao.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<Cnpj, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String digits;

        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        digits = value.replaceAll("\\D", "");
        if (digits.length() != 14 || hasAllDigitsEqual(digits)) {
            return false;
        }

        return calculateDigit(digits, 12) == Character.getNumericValue(digits.charAt(12))
                && calculateDigit(digits, 13) == Character.getNumericValue(digits.charAt(13));
    }

    private boolean hasAllDigitsEqual(String digits) {
        char first = digits.charAt(0);
        int index;

        for (index = 1; index < digits.length(); index++) {
            if (digits.charAt(index) != first) {
                return false;
            }
        }

        return true;
    }

    private int calculateDigit(String digits, int length) {
        int[] weights = length == 12
                ? new int[] { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 }
                : new int[] { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int sum = 0;
        int index;

        for (index = 0; index < length; index++) {
            sum += Character.getNumericValue(digits.charAt(index)) * weights[index];
        }

        sum = sum % 11;
        return sum < 2 ? 0 : 11 - sum;
    }
}
