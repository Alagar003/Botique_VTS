
package com.example.Boutique_Final.repositories;

import com.example.Boutique_Final.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUser_Id(String userId);

}

