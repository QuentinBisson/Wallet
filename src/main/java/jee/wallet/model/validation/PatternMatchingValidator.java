package fr.univ_rouen.bd.model.validation;

/**
 *
 * @author bissoqu1
 */
public class PatternMatchingValidator extends AbstractValidator<String> {

    private String pattern;

    public PatternMatchingValidator(String fieldName, String pattern) {
        super(fieldName);
        this.pattern = pattern;
    }

    @Override
    protected boolean validate(String e) {
        boolean result = true;
        Validator<String> validator = new EmptyStringValidator(getFieldName());
        if (!validator.isValid(e)) {
            addAllValidationMessage(validator.getValidationMessages());
            result = false;
        }
        
        if (result && !e.matches(pattern)) {
            addValidationMessage(getErrorName(), "Le valeur du champ " + getFieldName() + " ne correspond pas au format attendu");
            result = false;
        }
        return result;
    }
}
