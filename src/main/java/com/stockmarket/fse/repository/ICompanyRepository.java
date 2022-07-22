package com.stockmarket.fse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stockmarket.fse.model.Company;

@Repository
public interface ICompanyRepository extends MongoRepository<Company, Integer> {

}
