package com.example.Boutique_Final.Mapper;//package com.example.Boutique_Final.Mapper;
//
//import com.example.Boutique_Final.dto.CommentDTO;
//import com.example.Boutique_Final.model.Comment;
//import org.bson.types.ObjectId;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//@Mapper(componentModel = "spring")
//public interface CommentMapper {
//
//    // Convert ObjectId to String when mapping to DTO
//    @Mapping(target = "userId", source = "user.id", qualifiedByName = "mapObjectIdToString")
//    CommentDTO toDTO(Comment comment);
//
//    // Convert String back to ObjectId when mapping to Entity
//    @Mapping(target = "user.id", source = "userId", qualifiedByName = "mapStringToObjectId")
//    @Mapping(target = "product", ignore = true)
//    Comment toEntity(CommentDTO commentDTO);
//
//    // Custom method to convert ObjectId to String
//    @Named("mapObjectIdToString")
//    default String mapObjectIdToString(ObjectId objectId) {
//        return (objectId != null) ? objectId.toHexString() : null;
//    }
//
//    // Custom method to convert String to ObjectId
//    @Named("mapStringToObjectId")
//    default ObjectId mapStringToObjectId(String id) {
//        return (id != null && !id.isEmpty()) ? new ObjectId(id) : null;
//    }
//}




import com.example.Boutique_Final.dto.CommentDTO;
import com.example.Boutique_Final.model.Comment;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    // Map Comment entity to CommentDTO
    @Mapping(target = "userId", source = "user.id", qualifiedByName = "mapObjectIdToString")
    CommentDTO toDTO(Comment comment);

    // Map CommentDTO back to Comment entity
    @Mapping(target = "user.id", source = "userId", qualifiedByName = "mapStringToObjectId")
    Comment toEntity(CommentDTO commentDTO);

    // Convert List<CommentDTO> to List<Comment>
    List<Comment> toEntity(List<CommentDTO> commentDTOs);

    // Custom method to map ObjectId to String (for user.id)
    @Named("mapObjectIdToString")
    default String mapObjectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    // Custom method to map String to ObjectId (for user.id)
    @Named("mapStringToObjectId")
    default ObjectId mapStringToObjectId(String id) {
        return id != null && !id.isEmpty() ? new ObjectId(id) : null;
    }
}
