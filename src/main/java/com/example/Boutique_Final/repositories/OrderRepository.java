//package com.example.Boutique_Final.repositories;
//
//
//import com.example.Boutique_Final.model.Order;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
//public interface OrderRepository extends MongoRepository<Order, Long> {
//    List<Order> findByUserId(long userId);
//}


package com.example.Boutique_Final.repositories;

import com.example.Boutique_Final.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUser_Id(String userId);

}

