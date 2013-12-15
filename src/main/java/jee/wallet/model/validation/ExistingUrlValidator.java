package fr.univ_rouen.bd.model.validation;

import fr.univ_rouen.bd.model.validation.exception.ValidationException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author bissoqu1
 */
public class ExistingUrlValidator extends AbstractValidator<String> {
    private final List<String> acceptedContentTypes;

    public ExistingUrlValidator(String fieldName) {
        this(fieldName, null);
    }
    
    public ExistingUrlValidator(String fieldName, List<String> acceptedContentTypes) {
        super(fieldName);
        this.acceptedContentTypes = acceptedContentTypes;
    }

    private boolean exists(String url) {
        boolean result;
        try {
            HttpURLConnection.setFollowRedirects(false);
            // HttpURLConnection.setInstanceFollowRedirects(false);
            HttpURLConnection con =
                    (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            
            
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            
            if (result && CollectionUtils.isNotEmpty(acceptedContentTypes)) {
                if (!acceptedContentTypes.contains(con.getContentType())) {
                    result = false;
                }
            };
            return result;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }

    @Override
    protected boolean validate(String e) {
        boolean result = true;
        Validator<String> validator = new EmptyStringValidator(getFieldName());
        if (!validator.isValid(e)) {
            addAllValidationMessage(validator.getValidationMessages());
            result = false;
        }
        
        if (result && !exists(e)) {
            addValidationMessage(getErrorName(), "L'url fourni pour le champ " + getFieldName() + " ne correspond pas à une url fonctionnelle ou le Content-Type est invalide.");
            result = false;
        }
        return result;
    }
}
