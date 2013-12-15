package fr.univ_rouen.bd.model.validation;

/**
 *
 * @author bissoqu1
 */
public class MinLengthValidator extends AbstractValidator<String> {

    private final int length;

    public MinLengthValidator(String fieldName, int length) {
        super(fieldName);
        this.length = length;
    }

    @Override
    protected boolean validate(String e) {
        boolean result = true;
        Validator<String> validator = new EmptyStringValidator(getFieldName());
        validator.setErrorName(getErrorName());
        if (!validator.isValid(e)) {
            addAllValidationMessage(validator.getValidationMessages());
            result = false;
        }
        if (result && e.length() < length) {
            addValidationMessage(getErrorName(), "Le champ " + getFieldName() + " doit contenir au moins " + length + " caractères.");
            result = false;
        }
        return result;
    }
}
