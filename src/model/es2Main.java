package model;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class es2Main {

	public static void main(String[] args) {
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("siwEs2-unit");
		EntityManager em = emf.createEntityManager();

		Address addressRegini = new Address();
		addressRegini.setCity("Rome");
		addressRegini.setCountry("Italy");
		addressRegini.setState("Italy");
		addressRegini.setStreet("Largo Alberto Pepere 11");
		addressRegini.setZipCode("00151");


		Address addressMisco = new Address();
		addressMisco.setCity("London");
		addressMisco.setCountry("UK");
		addressMisco.setState("UK");
		addressMisco.setStreet("Street Dell Corporation");
		addressMisco.setZipCode("SW10 9WH");

		Address addressApple = new Address();
		addressApple.setCity("Cupertino");
		addressApple.setCountry("US");
		addressApple.setState("California");
		addressApple.setStreet("1 Infinite Loop");
		addressApple.setZipCode("001 001");



		Provider providerMisco = new Provider();
		providerMisco.setEmail("misco@info.com");
		providerMisco.setName("Misco");
		providerMisco.setPhoneNumber("800911911");
		providerMisco.setVatin("UK3456789");
		providerMisco.setAddress(addressMisco);


		Provider providerAppleStore = new Provider();
		providerAppleStore.setEmail("apple@info.com");
		providerAppleStore.setName("Apple");
		providerAppleStore.setPhoneNumber("199800800");
		providerAppleStore.setVatin("US23456789");
		providerAppleStore.setAddress(addressApple);


		Product productMonitor = new Product();
		productMonitor.setName("Moniotr Dell");
		productMonitor.setDescription("Questo Monitor Dell ha una risoluzione di 2k");
		productMonitor.setPrice(499.99F);
		productMonitor.setProviders(Collections.singletonList(providerMisco));

		providerMisco.setProducts(Collections.singletonList(productMonitor));

		Product productIphone = new Product();
		productIphone.setName("iPhone 6");
		productIphone.setDescription("Il miglior iPhone di sempre!");
		productIphone.setPrice(699.99F);
		productIphone.setProviders(Collections.singletonList(providerAppleStore));

		providerAppleStore.setProducts(Collections.singletonList(productIphone));

		OrderLine orderLine1 = new OrderLine();
		orderLine1.setProduct(productIphone);
		orderLine1.setQuantity(2);
		orderLine1.setUnitPrice(699.99F);


		OrderLine orderLine2 = new OrderLine();
		orderLine2.setProduct(productMonitor);
		orderLine2.setQuantity(3);
		orderLine2.setUnitPrice(499.99F);




		Customer customerRegini= new Customer();
		customerRegini.setAddress(addressRegini);
		Calendar dateOfBirthRegini = Calendar.getInstance();
		dateOfBirthRegini.set(1993, 9, 3);
		customerRegini.setDateOfBirth(dateOfBirthRegini.getTime());
		customerRegini.setEmail("nicco93@gmail.com");
		customerRegini.setFirstName("Niccolo'");
		customerRegini.setLastName("Regini");
		customerRegini.setPhoneNumber("3346399479");
		customerRegini.setAddress(addressRegini);


		Order order = new Order();
		customerRegini.setOrders(Collections.singletonList(order));
		order.setCustomer(customerRegini);
		order.addOrderLine(orderLine1);
		order.addOrderLine(orderLine2);



		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(productMonitor);
		em.persist(productIphone);
		
		em.persist(providerMisco);
		em.persist(providerAppleStore);
		
		em.persist(customerRegini);
		
		em.persist(order);

		tx.commit();

		em.close();
		emf.close();

		CustomerFacade cf= new CustomerFacade();
		List<Customer> listaCustomer = cf.findAllCustomers();
		for(Customer customer : listaCustomer){
			System.out.println(customer.toString());
			System.out.println("\n");
		}
		if(cf.getOrders(451L)!=null)
			System.out.println(cf.getOrders(451L).toString());
		
		ProductFacade pf= new ProductFacade();
		List<Provider> listaProvider = pf.retriveProviders2(new Long(902));
		if(listaProvider!=null)
			System.out.println(listaProvider.toString());
	}
}
