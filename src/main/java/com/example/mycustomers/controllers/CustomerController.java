package com.example.mycustomers.controllers;

import com.example.mycustomers.model.Customer;
import com.example.mycustomers.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = {"/{id}"})
    ResponseEntity byId(@PathVariable String id){
        if(!customerRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404

        return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK); // HTTP 200
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    ResponseEntity<?> save(@RequestBody Customer customer){

        if(customer == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // HTTP 400
        if(customerRepository.existsById(customer.getId()))
            return new ResponseEntity(HttpStatus.CONFLICT); // HTTP 409

        Customer cust = customerRepository.save(customer);
        return new ResponseEntity<>(cust, HttpStatus.CREATED); // HTTP 201
    }

    @RequestMapping(value = {""}, method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody Customer customer){
        if(customer == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // HTTP 400
        if(!customerRepository.existsById(customer.getId()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // HTTP 400

        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED); // HTTP 201
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Object> delete(@PathVariable String id){
        if(!customerRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // HTTP 400

        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // HTTP 204
    }

}
