package cleanarch.poc.usecases.usecases;

import cleanarch.poc.domain.entities.model.Contract;
import cleanarch.poc.domain.entities.model.Customer;

import java.util.List;

public interface CustomerEnrollment {
    Contract enrollNewCustomer(Customer customer);
    List<Contract> unEnrollCustomer(Customer customer);
}
