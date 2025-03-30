package com.example.Boutique_Final.Mapper;

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
