//package com.example.Boutique_Final.Mapper;
//
//import org.bson.types.ObjectId;
//import org.mapstruct.Named;
//import org.springframework.stereotype.Component;
//
//@Component // Ensure Spring recognizes this as a Bean
//public class ObjectIdMapper {
//
//    @Named("mapObjectIdToString")
//    public String mapObjectIdToString(ObjectId objectId) {
//        return (objectId != null) ? objectId.toHexString() : null;
//    }
//
//    @Named("mapStringToObjectId")
//    public ObjectId mapStringToObjectId(String id) {
//        return (id != null && !id.isEmpty()) ? new ObjectId(id) : null;
//    }
//}

