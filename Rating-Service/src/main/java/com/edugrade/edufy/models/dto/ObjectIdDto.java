package com.edugrade.edufy.models.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

public class ObjectIdDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId objectId;

    public ObjectIdDto() {
    }

    public ObjectIdDto(ObjectId objectId) {
        this.objectId = objectId;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }
}
