package com.ebugtracker.admin.dao;

import com.ebugtracker.admin.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffRepository extends JpaRepository<Staff,Long> {
}
