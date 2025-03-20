////package com.example.Boutique_Final.repositories;
////
////import com.example.Boutique_Final.model.Cart;
////import org.bson.types.ObjectId;
////import org.springframework.data.mongodb.repository.MongoRepository;
////import org.springframework.data.mongodb.repository.Query;
////
////import java.util.List;
////import java.util.Optional;
////
////public interface CartRepository extends MongoRepository<Cart, String> {
////
////    // Query to find a cart by the user's ID (referenced in the user object)
////    @Query("{ 'user.id': ?0 }")
////    Optional<Cart> findByUserId(ObjectId userId);
////
////    @Query("{ 'user.id': ?0 }")
////    Optional<Cart> findCartByUserId(Long userId);
////
////
////
////
////
////    // Query to find a cart by the user's email
////    @Query("{ 'user.email': ?0 }")
////    Optional<Cart> findByEmail(String email);
////
////    Optional<Cart> findByUserEmail(String email);
////
////
////    // Query to find a cart by user ID with items that exist and are not empty
////    @Query("{ 'user.id': ?0, 'items': { $exists: true, $ne: [] } }")
////    Optional<Cart> findByUserIdWithNonEmptyItems(ObjectId userId);
////
////    // Optional: Query to count carts for a specific user (useful for debugging or validations)
////    @Query(value = "{ 'user.id': ?0 }", count = true)
////    long countByUserId(ObjectId userId);
////
////    @Query("{ 'user.id': ?0 }")
////    List<Cart> findAllByUserId(ObjectId userId);
////}
//
//
//
//
//package com.example.Boutique_Final.repositories;
//
//import com.example.Boutique_Final.model.Cart;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CartRepository extends MongoRepository<Cart, ObjectId> {
//
//    // ✅ Find cart by user's ObjectId (assuming "user.id" is the correct field in MongoDB)
//    @Query("{ 'user.id': ?0 }") // If `userId` is stored inside `user`
//    Optional<Cart> findByUserId(ObjectId userId);
//
//
//    // ✅ Find cart by user email
//    @Query("{ 'user.email': ?0 }")
//    Optional<Cart> findByUser_Id(ObjectId userId);
//
//
//
//    // ✅ Find cart by user ID where items exist and are not empty
//    @Query("{ 'user.id': ?0, 'items': { $exists: true, $ne: [] } }")
//    Optional<Cart> findByUserIdWithNonEmptyItems(ObjectId userId);
//
//    // ✅ Count carts for a specific user
//    @Query(value = "{ 'user.id': ?0 }", count = true)
//    long countByUserId(ObjectId userId);
//
//    // ✅ Find all carts by user ID
//    @Query("{ 'user.id': ?0 }")
//    List<Cart> findAllByUserId(ObjectId userId);
//
//    // ✅ Delete cart by user ID
//    void deleteByUserId(ObjectId userId);
//
//
//
//
//
//
//
//
//}



package com.example.Boutique_Final.repositories;

import com.example.Boutique_Final.model.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {

    // ✅ Find cart by user's ID (ObjectId)
    @Query("{ 'user.id': ?0 }")
    Optional<Cart> findByUserId(ObjectId userId);

    // ✅ Find cart by user email
    @Query("{ 'user.email': ?0 }")
    Optional<Cart> findByUserEmail(String email);

    // ✅ Find cart by user ID where items exist and are not empty
    @Query("{ 'user.id': ?0, 'items': { $exists: true, $ne: [] } }")
    Optional<Cart> findByUserIdWithNonEmptyItems(ObjectId userId);

    // ✅ Count carts for a specific user
    @Query(value = "{ 'user.id': ?0 }", count = true)
    long countByUserId(ObjectId userId);

    // ✅ Find all carts by user ID
    @Query("{ 'user.id': ?0 }")
    List<Cart> findAllByUserId(ObjectId userId);

    // ✅ Delete cart by user ID
    void deleteByUserId(ObjectId userId);
}
