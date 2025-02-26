package com.keepintouch.kit.forms;

public class ContactSearchForm {
    private String field;
    private String value;

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactSearchForm(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public ContactSearchForm() {
    }

    @Override
    public String toString() {
        return "ContactSearchForm{" +
                "field='" + field + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
