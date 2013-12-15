package fr.univ_rouen.bd.model.validation;

import org.apache.commons.lang.StringUtils;


/**
 *
 * @author bissoqu1
 */
public class EmptyStringValidator extends AbstractValidator<String> {

    public EmptyStringValidator(String fieldName) {
        super(fieldName);
    }
    
    @Override
    protected boolean validate(String e) {
        boolean result = StringUtils.isNotBlank(e);
        if (!result) {
            addValidationMessage(getErrorName(), "Le champ " + getFieldName() + " ne peut être vide");
        }
        return result;
    }
    
}
