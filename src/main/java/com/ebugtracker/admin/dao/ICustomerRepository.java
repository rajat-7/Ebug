package com.ebugtracker.admin.dao;

import com.ebugtracker.admin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
