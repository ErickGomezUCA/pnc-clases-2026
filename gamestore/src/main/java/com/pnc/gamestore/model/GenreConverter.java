package com.pnc.gamestore.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {

    @Override
    public String convertToDatabaseColumn(Genre attribute) {
        if (attribute == null) return null;
        return attribute.getValue();
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Genre.fromValue(dbData);
    }
}