package com.ebugtracker.admin.service;

import com.ebugtracker.admin.constants.BugStatus;
import com.ebugtracker.admin.dao.IBugRepository;
import com.ebugtracker.admin.dao.ICustomerRepository;
import com.ebugtracker.admin.dao.IStaffRepository;
import com.ebugtracker.admin.entity.Bug;
import com.ebugtracker.admin.entity.Customer;
import com.ebugtracker.admin.entity.Staff;
import com.ebugtracker.admin.exceptions.StaffNotFoundException;
import com.ebugtracker.admin.util.AdminUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StaffServiceImpl implements IStaffService{

    @Autowired
    private IStaffRepository staffRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IBugRepository bugRepository;

    @Autowired
    private AdminUtility utility;

    @Override
    public Staff addStaff(Staff staff) {
        staffRepository.save(staff);
        return staff;
    }

    @Override
    public Staff editStaff(Staff staff) {
        Staff required=findStaffById(staff.getStaffId());
        required.setStaffName(staff.getStaffName());
        required.setDepartment(staff.getDepartment());
        required.setAssignedTicketIds(staff.getAssignedTicketIds());
        staffRepository.save(required);
        return required;
    }

    @Override
    public void deleteStaff(long id) {
        Staff staff=findStaffById(id);
        staffRepository.delete(staff);
    }

    @Override
    public Staff findStaffById(long id) {
        Optional<Staff> optional= staffRepository.findById(id);
        if(optional.isEmpty()){
            throw new StaffNotFoundException("Staff is not found for the id : "+id);
        }
        return optional.get();
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public List<Bug> getBugReport() {
        return utility.getAllBugs();
    }

    @Override
    public List<Bug> getBugReportByPriority(String level) {
        List<Bug> bugs=getBugReport();
        return bugs.stream()
                .filter(n->n.getBugPriority().toString().equalsIgnoreCase(level))
                .collect(Collectors.toList());
    }

    @Override
    public Staff assignBugToStaff(Long staffId, Long ticketId) {
        Staff staff=findStaffById(staffId);
        staff.getAssignedTicketIds().add(ticketId);
        Bug bug=utility.findBugById(ticketId);
        bug.setBugStatus(BugStatus.ASSIGNED);
        bugRepository.save(bug);
        staffRepository.save(staff);
        return staff;
    }

    @Override
    public Customer sendMessageToCustomer(Long customerId,String msg) {
        return utility.sendMessageToCustomer(customerId,msg);

    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
        
    }

}
