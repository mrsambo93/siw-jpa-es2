package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerFacadeTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	private	CustomerFacade customerFacade;
	
	@BeforeClass
	public static void initEntityManager(){
		emf= Persistence.createEntityManagerFactory("siwEs2-unit-test");
		em=emf.createEntityManager();
	}
	
	@AfterClass
	public static void closeEntityManager(){
		if(em!=null)
			em.close();
		if(emf!=null)
			emf.close();
	}
	
	@Before
	public void initTransaction(){
		tx=em.getTransaction();
	}
	
	@Before
	public void setUp() throws Exception {
		this.customerFacade = new CustomerFacade();
	}

	@Test
	public void testInsertCustomer() {
		Customer customerAtteso=this.customerFacade.createCustomer("NomeTEST", "CognomeTEST", "EmailTEST", "TelefonoTEST", new Date(), "StradaTEST", "CittaTEST", "StatoTEST", "ZipCodeTEST", "CountryTEST");
		assertNotNull(customerAtteso);
		assertNotNull("ID should not be null", customerAtteso.getId());

		tx.begin();
		
		Customer customerEffettivo= CustomerFacadeTest.em.find(Customer.class, new Long(customerAtteso.getId()));
		assertNotNull(customerEffettivo);
		assertEquals(customerAtteso, customerEffettivo);
		
		tx.commit();
		
	}


	@Test
	public void testFindAllCustomer() {
		List<Customer> customerList = customerFacade.findAllCustomers();
		assertNotNull(customerList);
		
		int size= this.customerFacade.findAllCustomers().size();
		this.customerFacade.createCustomer("Nome", "Cognome", "Email", "Telefono", new Date(), "Strada", "Citta", "Stato", "ZipCode", "Country");
		assertEquals(size+1, this.customerFacade.findAllCustomers().size());	
	}

	@Test
	public void testGetOrders() {
		fail("Not yet implemented");
	}
}


