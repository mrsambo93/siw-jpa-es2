package model;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CustomerFacade{
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	public CustomerFacade(){
		}
	
	private void openEntityManager(){
		this.emf= Persistence.createEntityManagerFactory("siwEs2-unit");
		this.em= emf.createEntityManager();
	}

	private void closeEntityManager(){
		if(em!=null)
			em.close();
		if(emf!=null)
			emf.close();
	}
	
	private void initTransaction(){
		tx= em.getTransaction();
	}
	
	public Customer createCustomer(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, String street, String city, String state, String zipCode, String country){
		this.openEntityManager();
		
		Address address = new Address(street, city, state, zipCode, country);
		Customer customer= new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setDateOfBirth(dateOfBirth);
		customer.setAddress(address);
		
		this.initTransaction();
		tx.begin();
		try{
			em.persist(customer);
			tx.commit();
			return customer;
		}catch(Exception e){
			tx.rollback();
			return null;
		}finally{//Il costrutto finally viene eseguito ogni volta
			this.closeEntityManager();
		}
		
	}
	
	public List<Customer> findAllCustomers(){
		this.openEntityManager();			
		this.initTransaction();
		try{
			tx.begin();
			TypedQuery<Customer> result = this.em.createNamedQuery("findAllCustomer",Customer.class);
			tx.commit();
			return result.getResultList();
		}catch(Exception e){
			tx.rollback();
			return null;
		}finally{
			this.closeEntityManager();
		}
		
	}
	
	public List<Order> getOrders(Long customerID){
		this.openEntityManager();
		this.tx= em.getTransaction();
		try{
			tx.begin();
			Customer customer = this.em.find(Customer.class, customerID);
			tx.commit();
			return customer.getOrders();
		}catch(Exception e){
			tx.rollback();
			return null;
		}finally{
			this.closeEntityManager();
		}
	}
}
