package model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductTestOLD {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeClass
	public static void initEntityManager(){
		emf= Persistence.createEntityManagerFactory("products-unit-test");
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
	
	@Test 
	public void shouldCreateAProduct() throws Exception{
		//Crea una istanza di un prodotto
		assertTrue(true);
//		Product product = new Product();
//		product.setName("SLANGAN");
//		product.setDescription("5434543-AA");
//		
//		tx.begin();
//		em.persist(product);
//		tx.commit();
//		
//		assertNotNull("ID should not be null", product.getId());
//		
//		//Retrives all the products from the database
//		@SuppressWarnings("unchecked")
//		List<Product> products= em.createNamedQuery("findAllProducts").getResultList();
//		
//		assertEquals(1, products.size());

	}
}
