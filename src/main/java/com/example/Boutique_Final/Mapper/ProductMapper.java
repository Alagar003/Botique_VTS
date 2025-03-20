package com.example.Boutique_Final.Mapper;//package com.example.Boutique_Final.Mapper;
//
//import com.example.Boutique_Final.dto.ProductDTO;
//import com.example.Boutique_Final.model.Product;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface ProductMapper {
//
//    @Mapping(target = "id", expression = "java(product.getId() != null ? product.getId().toHexString() : null)") // Convert ObjectId to String
//    @Mapping(target = "image", source = "image")
//    ProductDTO toDTO(Product product);
//
//    @Mapping(target = "id", expression = "java(productDTO.getId() != null ? new org.bson.types.ObjectId(productDTO.getId()) : null)") // Convert String to ObjectId
//    @Mapping(target = "image", source = "image")
//    Product toEntity(ProductDTO productDTO);
//
////    @Mapping(target = "userId", expression = "java(comment.getUser() != null && comment.getUser().getId() != null ? comment.getUser().getId().toHexString() : null)") // Convert ObjectId to String
////    CommentDTO toDTO(Comment comment);
////
////    @Mapping(target = "user.id", expression = "java(commentDTO.getUserId() != null ? new org.bson.types.ObjectId(commentDTO.getUserId()) : null)") // Convert String to ObjectId
////    @Mapping(target = "product", ignore = true)
////    Comment toEntity(CommentDTO commentDTO);
//
//    // Helper methods for lists can be added if needed.
//
//
//}



//package com.example.Boutique_Final.Mapper;
//
//import com.example.Boutique_Final.dto.ProductDTO;
//import com.example.Boutique_Final.model.Product;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface ProductMapper {
//
//    @Mapping(target = "id", expression = "java(product.getId() != null ? product.getId().toHexString() : null)") // Convert ObjectId to String
//    @Mapping(target = "image", source = "image")
//    ProductDTO toDTO(Product product);
//
//    @Mapping(target = "id", expression = "java(productDTO.getId() != null ? new org.bson.types.ObjectId(productDTO.getId()) : null)") // Convert String to ObjectId
//    @Mapping(target = "image", source = "image")
//    Product toEntity(ProductDTO productDTO);
//
////    @Mapping(target = "userId", expression = "java(comment.getUser() != null && comment.getUser().getId() != null ? comment.getUser().getId().toHexString() : null)") // Convert ObjectId to String
////    CommentDTO toDTO(Comment comment);
////
////    @Mapping(target = "user.id", expression = "java(commentDTO.getUserId() != null ? new org.bson.types.ObjectId(commentDTO.getUserId()) : null)") // Convert String to ObjectId
////    @Mapping(target = "product", ignore = true)
////    Comment toEntity(CommentDTO commentDTO);
//
//    // Helper methods for lists can be added if needed.
//}


//import com.example.Boutique_Final.Mapper.CommentMapper;
//import com.example.Boutique_Final.dto.CommentDTO;
//import com.example.Boutique_Final.dto.ProductDTO;
//import com.example.Boutique_Final.model.Comment;
//import com.example.Boutique_Final.model.Product;
//import org.bson.types.ObjectId;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Mapper(componentModel = "spring", uses = CommentMapper.class)
//public interface ProductMapper {
//
//    // Map Product to ProductDTO with comments field mapping
//    @Mapping(target = "comments", source = "comments", qualifiedByName = "mapComments") // Use mapComments for List<Comment> to List<CommentDTO>
//    ProductDTO toDTO(Product product);
//
//    // Custom method to convert List<Comment> to List<CommentDTO with ObjectId to String conversion
//    @Named("mapComments")
//    default List<CommentDTO> mapComments(List<Comment> comments) {
//        if (comments == null) {
//            return null;
//        }
//        return comments.stream()
//                .map(comment -> new CommentDTO(
//                        comment.getId().toHexString(), // Convert ObjectId to String
//                        comment.getContent(),
//                        comment.getUser() != null ? comment.getUser().getId().toHexString() : null // Assuming User has ObjectId
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // Custom method to convert ObjectId to String for individual Comment IDs
//    @Named("mapObjectIdToString")
//    default String mapObjectIdToString(ObjectId objectId) {
//        return (objectId != null) ? objectId.toHexString() : null;
//    }
//
//}


import com.example.Boutique_Final.dto.ProductDTO;
import com.example.Boutique_Final.model.Product;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Map ObjectId to String for ProductDTO
    @Mapping(target = "id", source = "id", qualifiedByName = "mapObjectIdToString")
    ProductDTO toDTO(Product product);

    // Map String to ObjectId for Product entity
    @Mapping(target = "id", source = "id", qualifiedByName = "mapStringToObjectId")
    Product toEntity(ProductDTO productDTO);

    // Custom method to map ObjectId to String
    @Named("mapObjectIdToString")
    default String mapObjectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    // Custom method to map String to ObjectId
    @Named("mapStringToObjectId")
    default ObjectId mapStringToObjectId(String id) {
        return id != null && !id.isEmpty() ? new ObjectId(id) : null;
    }
}
