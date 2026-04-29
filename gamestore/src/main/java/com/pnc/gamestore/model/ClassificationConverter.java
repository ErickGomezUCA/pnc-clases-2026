package com.pnc.gamestore.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClassificationConverter implements AttributeConverter<Classification, String> {

    @Override
    public String convertToDatabaseColumn(Classification attribute) {
        if (attribute == null) return null;
        return attribute.getValue(); // stores "E10+" in DB
    }

    @Override
    public Classification convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Classification.fromValue(dbData); // reads "E10+" → E_PLUS_10
    }
}