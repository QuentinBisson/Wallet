package fr.univ_rouen.bd.model.validation;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public class MaxLengthValidator extends AbstractValidator<String> {

    private final int length;

    public MaxLengthValidator(String fieldName, int length) {
        super(fieldName);
        this.length = length;
    }

    @Override
    protected boolean validate(String e) {
        boolean result = true;
        Validator<String> validator = new EmptyStringValidator(getFieldName());
        if (!validator.isValid(e)) {
            addAllValidationMessage(validator.getValidationMessages());
            result = false;
        }
        if (result && e.length() > length) {
            addValidationMessage(getErrorName(), "Le champ " + getFieldName() + " doit contenir moins de " + length + " caractères.");
            result = false;
        }
        return result;
    }
}
