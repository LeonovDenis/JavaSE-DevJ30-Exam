package org.dictionary.model;

import java.io.Serializable;

public class Term implements Serializable {
    private String name;
    private String description;

    public Term(String name) {
        this.name = name;
    }
    
    public Term(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
