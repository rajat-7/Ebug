package com.ebugtracker.admin.entity;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

//import javax.crypto.Mac;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
public class Staff {
    public Staff(long staffId, @NotBlank @Length(max = 15, min = 1) String staffName,
            @NotBlank @Length(max = 10, min = 1) String department, Set<Long> assignedTicketIds) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.department = department;
        this.assignedTicketIds = assignedTicketIds;
    }

    public Staff(){}

    public long getStaffId() {
        return staffId;
    }
    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Set<Long> getAssignedTicketIds() {
        return assignedTicketIds;
    }
    public void setAssignedTicketIds(Set<Long> assignedTicketIds) {
        this.assignedTicketIds = assignedTicketIds;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long staffId;
    @NotBlank
    @Length(max = 15, min = 1)
    private String staffName;
    @NotBlank
    @Length(max = 10, min = 1)
    private String department;
    @ElementCollection
    Set<Long> assignedTicketIds;
}
