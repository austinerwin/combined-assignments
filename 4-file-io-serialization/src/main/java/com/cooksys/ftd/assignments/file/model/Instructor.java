package com.cooksys.ftd.assignments.file.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

public class Instructor {
    private Contact contact;

    @XmlElement
    public Contact getContact() {
        return contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
