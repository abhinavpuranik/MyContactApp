package com.seveneleven.mycontact.contact.Model;

import java.util.Objects;

public class Tag {

    private String name;

    public Tag(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tag name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    // IMPORTANT for Set uniqueness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        return name.equalsIgnoreCase(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name;
    }
}