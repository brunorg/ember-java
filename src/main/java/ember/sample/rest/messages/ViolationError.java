package ember.sample.rest.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViolationError extends Message {

    @JsonProperty("invalid_value")
    private Object invalidValue;
    @JsonProperty("property")
    private String propertyPath;
    @JsonProperty("class_name")
    private String rootBeanClass;

    public ViolationError() {
        super("ViolationError");
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public String getRootBeanClass() {
        return rootBeanClass;
    }

    public void setRootBeanClass(String rootBeanClass) {
        this.rootBeanClass = rootBeanClass;
    }
}