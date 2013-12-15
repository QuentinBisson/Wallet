package jee.wallet.model.validation;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bissoqu1
 */
public abstract class AbstractValidator<E> implements Validator<E> {

    private Map<String, List<String>> messages;
    private String fieldName;
    private String errorName;

    public AbstractValidator(String fieldName) {
        this.fieldName = fieldName;
        messages = new HashMap<String, List<String>>();
    }

    @Override
    public Map<String, List<String>> getValidationMessages() {
        return messages;
    }

    protected String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String s) {
        this.fieldName = s;
    }

    protected String getErrorName() {
        return errorName;
    }

    @Override
    public void setErrorName(String s) {
        this.errorName = s;
    }

    protected void addValidationMessage(String field, String message) {
        List<String> l;
        if (CollectionUtils.isNotEmpty(this.messages.get(field))) {
            l = this.messages.get(field);
        } else {
            l = new ArrayList<String>();
        }
        l.add(message);
        this.messages.put(field, l);
    }

    protected void addAllValidationMessage(Map<String, List<String>> m) {
        for (String key : m.keySet()) {
            List<String> l = m.get(key);
            for (String s : l) {
                addValidationMessage(key, s);
            }
        }
    }

    protected abstract boolean validate(E e);

    @Override
    public boolean isValid(E e) {
        return validate(e);
    }
}
