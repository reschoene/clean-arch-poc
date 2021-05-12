package cleanarch.poc.domain.services.usecases;

import cleanarch.poc.domain.model.Contract;
import cleanarch.poc.domain.model.Customer;

import java.util.List;

public interface CustomerEnrollment {
    Contract enrollNewCustomer(Customer customer);
    List<Contract> unEnrollCustomer(Customer customer);
}
