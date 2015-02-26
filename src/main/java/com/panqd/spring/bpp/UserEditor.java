package com.panqd.spring.bpp;

import java.beans.PropertyEditorSupport;

public class UserEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] attrs = text.split(",");
        int number = Integer.parseInt(attrs[1]);
        User u = new User();
        u.setUsername(attrs[0]);
        u.setNumber(number);
        this.setValue(u);
    }
    
}
