package fr.univ_rouen.bd.model.validation;

/**
 *
 * @author bissoqu1
 */
public class MIMETypeValidator extends AbstractValidator<String> {
    
    private String mimeType;
    
    public MIMETypeValidator(String fieldName,String mimeType) {
        super(fieldName);
        this.mimeType = mimeType;
    }
    
    @Override
    protected boolean validate(String e) {
        boolean result = mimeType.equals(e);
        if (!result) {
            addValidationMessage(getErrorName(), "Le type MIME du fichier " + e + " ne correspond pas au type attendu (" + mimeType + ").");
        }
        return result;
    }
}
