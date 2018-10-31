package com.example.mycustomers.repositories;

import com.example.mycustomers.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
