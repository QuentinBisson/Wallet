package fr.univ_rouen.bd.model.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bissoqu1
 */
public abstract class AbstractForm<E> implements Form<E> {

    private Map<String, List<String>> errors;
    private String result;

    public AbstractForm() {
        errors = new HashMap<String, List<String>>();
    }

    @Override
    public Map<String, List<String>> getErrors() {
        return MapUtils.unmodifiableMap(errors);
    }

    protected void addError(String field, String message) {
        List<String> l;
        if (CollectionUtils.isEmpty(errors.get(field))) {
            l = new ArrayList<String>();
            errors.put(field, l);
            
        } else {
            l = errors.get(field);
        }
        l.add(message);        
    }

    protected void addAllErrors(String field, List<String> messages) {
        for (String m : messages) {
            addError(field, m);
        }
    }    
    
    @Override
    public String getResult() {
        return result;
    }

    @Override
    public boolean isValid() {
        return MapUtils.isEmpty(errors);
    }

    protected void setResult(String result) {
        this.result = result;
    }

    protected static String getFieldValue(HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            return StringUtils.trim(value);
        }
    }
}
