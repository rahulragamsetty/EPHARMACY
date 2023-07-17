package com.epharmacy.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epharmacy.dto.CustomerAddressDTO;
import com.epharmacy.dto.CustomerDTO;
import com.epharmacy.dto.PrimePlansDTO;
import com.epharmacy.entity.Customer;
import com.epharmacy.entity.CustomerAddress;
import com.epharmacy.entity.PrimePlans;
import com.epharmacy.exception.EPharmacyException;
import com.epharmacy.repository.CustomerAddressRepository;
import com.epharmacy.repository.CustomerRepository;
import com.epharmacy.repository.PrimePlansRepository;



@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PrimePlansRepository plansRepository;
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	private CustomerAddressDTO mdto = new CustomerAddressDTO();
	private CustomerAddress mentity = new CustomerAddress();
	private Customer centity = new Customer();
	private CustomerDTO cdto = new CustomerDTO();
	private PrimePlansDTO pdto = new PrimePlansDTO();
	

	@Override
	public String authenticateCustomer(String emailId, String password) throws Exception {
		Optional<Customer> optional = customerRepository.findByCustomerEmailId(emailId);
		Customer customer = optional.orElseThrow(()->new EPharmacyException("CUSTOMER_DOESNT_EXISTS"));
		if(!customer.getPassword().equals(password))
		{
			throw new EPharmacyException("PASSWORD_INCORRECT");
		}
		return "Valid user";

		}

	@Override
	public String registerNewCustomer(CustomerDTO customerDTO) throws Exception {
		Optional<Customer> optional = customerRepository.findByCustomerEmailId(customerDTO.getCustomerEmailId());
		// inform about existing case
		if(optional.isPresent()) {
			throw new EPharmacyException("USER_ALREADY_EXISTS");
		}
		Customer customer = centity.dtoToEntity(customerDTO);
		customer.setPlanExpiryDate(customerDTO.getPlanExpiryDate());
		customerRepository.save(customer);
		return "Registered successfully";
	}

	@Override
	public CustomerDTO viewCustomer(String email) throws EPharmacyException {
		Optional<Customer> optional = customerRepository.findByCustomerEmailId(email);
		Customer customer = optional.orElseThrow(()->new EPharmacyException("CUSTOMER_DOESNT_EXISTS"));
		CustomerDTO customerDTO = cdto.entityToDto(customer);
		List<CustomerAddressDTO> addressDTOs = new ArrayList<>();
		if(customer.getAddressList().isEmpty()) {
			customerDTO.setAddressList(Collections.emptyList());
		}
		else
		{
		for (CustomerAddress customerAddress : customer.getAddressList()) {
			CustomerAddressDTO customerAddressDTO = mdto.entityToDto(customerAddress);
			addressDTOs.add(customerAddressDTO);
		}
		customerDTO.setAddressList(addressDTOs);
		}
		if(!(customer.getPlan()==null)) {
		 PrimePlansDTO primePlansDTO =pdto.entityToDto(customer.getPlan());
		 customerDTO.setPlan(primePlansDTO);
		}
		 customerDTO.setPlanExpiryDate(customer.getPlanExpiryDate());
		 return customerDTO;
	}

	@Override
	public LocalDate upgradeCustomerToPrime(CustomerDTO customerDTO) throws EPharmacyException {
		Optional<PrimePlans> optional =plansRepository.findByPlanName(customerDTO.getPlan().getPlanName());
		PrimePlans plans = optional.get();
		Optional<Customer> optional2 = customerRepository.findByCustomerEmailId(customerDTO.getCustomerEmailId());
		Customer customer = optional2.orElseThrow(()->new EPharmacyException("CUSTOMER_DOESNT_EXISTS"));
		customer.setPlan(plans);
		if(plans.getPlanName().equals("YEARLY"))
			customer.setPlanExpiryDate(LocalDate.now().plusYears(1));
		else if(plans.getPlanName().equals("QUARTERLY"))
			customer.setPlanExpiryDate(LocalDate.now().plusMonths(3));
		else if(plans.getPlanName().equals("MONTHLY"))
			customer.setPlanExpiryDate(LocalDate.now().plusMonths(1));
		return customer.getPlanExpiryDate();
	}

	@Override
	public PrimePlansDTO getPlan(String email) throws EPharmacyException {
		Optional<Customer> optional = customerRepository.findByCustomerEmailId(email);
		Customer customer = optional.orElseThrow(()->new EPharmacyException("CUSTOMER_DOESNT_EXISTS"));
		if(customer.getPlan()==null)throw new EPharmacyException("NO_PLAN_AVAILABLE");
		PrimePlansDTO primePlansDTO =pdto.entityToDto(customer.getPlan());
		return primePlansDTO;
	}

	@Override
	public String deleteAddress(Integer addressId) throws EPharmacyException {
		Optional<CustomerAddress> optional = customerAddressRepository.findById(addressId);
		CustomerAddress customerAddress = optional.orElseThrow(()->new EPharmacyException("ADDRESS_NOT_EXISTS"));
		customerAddressRepository.delete(customerAddress);
		return "Deleted the address with id: "+addressId;
	}

	@Override
	public String addCustomerAddress(CustomerAddressDTO caDTO, String email) throws EPharmacyException {
		Optional<Customer> optional = customerRepository.findByCustomerEmailId(email);
		Customer customer = optional.orElseThrow(()->new EPharmacyException("CUSTOMER_DOESNT_EXISTS"));
		CustomerAddress customerAddress = mentity.dtoToEntity(caDTO);
		customer.getAddressList().add(customerAddress);
		return "Address is added";
	}
	
	

	@Override
	public List<CustomerAddressDTO> viewAllAddress(String email) throws EPharmacyException {
		Optional<Customer> optional = customerRepository.findByCustomerEmailId(email);
		Customer customer = optional.orElseThrow(()->new EPharmacyException("CUSTOMER_DOESNT_EXISTS"));
		List<CustomerAddressDTO> addressDTOs = new ArrayList<>();
		for (CustomerAddress customerAddress : customer.getAddressList()) {
			CustomerAddressDTO customerAddressDTO = mdto.entityToDto(customerAddress);
			addressDTOs.add(customerAddressDTO);
		}
		if(addressDTOs.isEmpty()) return Collections.emptyList();
		return addressDTOs;
	}

	@Override
	public CustomerAddressDTO getAddress(Integer deliveryId) throws EPharmacyException {
		Optional<CustomerAddress> optional = customerAddressRepository.findById(deliveryId);
		CustomerAddress customerAddress = optional.orElseThrow(()->new EPharmacyException("ADDRESS_NOT_EXISTS"));
		CustomerAddressDTO customerAddressDTO = mdto.entityToDto(customerAddress);
		return customerAddressDTO;
	}


	
	
	
}
