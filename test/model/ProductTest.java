package model;

import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Test;

public class ProductTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;

	@Test
	public void shouldCreateAProduct(){
		emf= Persistence.createEntityManagerFactory("products-unit-test");
		em=emf.createEntityManager();
		tx=em.getTransaction();
		Product product = new Product();
		product.setName("SLANGAN");
		product.setDescription("5434543-AA");
		
		tx.begin();
		em.persist(product);
		tx.commit();
		
		assertNotNull("ID should not be null", product.getId());
		
		//Retrives all the products from the database
//		@SuppressWarnings("unchecked")
//		List<Product> products= em.createNamedQuery("findAllProducts").getResultList();
//		
//		assertEquals(1, products.size());
		
		if(em!=null)
			em.close();
		if(emf!=null)
			emf.close();
	}

}
