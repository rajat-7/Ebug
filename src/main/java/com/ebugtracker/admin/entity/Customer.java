package com.ebugtracker.admin.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/* import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; */
import org.hibernate.validator.constraints.Length;


@Entity
public class Customer {
    public Customer(Long customerId, @NotBlank @Length(max = 20, min = 1) String customerName,
            List<String> messagesReceived, Set<Long> ticketsRaised) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.messagesReceived = messagesReceived;
        this.ticketsRaised = ticketsRaised;
    }
    public Customer(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customerId;
    @NotBlank
    @Length(max = 20, min = 1)
    String customerName;
    @ElementCollection
    List<String> messagesReceived;
    @ElementCollection
    Set<Long> ticketsRaised;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public List<String> getMessagesReceived() {
        return messagesReceived;
    }
    public void setMessagesReceived(List<String> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }
    public Set<Long> getTicketsRaised() {
        return ticketsRaised;
    }
    public void setTicketsRaised(Set<Long> ticketsRaised) {
        this.ticketsRaised = ticketsRaised;
    }
}
