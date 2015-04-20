package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ProductFacade {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	public ProductFacade() {}
	
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
	
	public List<Provider> retriveProviders(Long productID) {
		this.openEntityManager();			
		this.initTransaction();
		try{
			tx.begin();
			List<Provider> listaProvider = this.em.find(Product.class, productID).getProviders();
			tx.commit();
			return listaProvider;
		}catch(Exception e){
			tx.rollback();
			return null;
		}finally{
			this.closeEntityManager();
		}
	}
	
	public List<Provider> retriveProviders2(Long productID) {
		this.openEntityManager();			
		this.initTransaction();
		try{
			tx.begin();
			Query result = this.em.createNamedQuery("findProduct",Product.class)
											.setParameter("productID", productID);
			tx.commit();
			Product product = (Product) result.getSingleResult();
			return product.getProviders();
			
		}catch(Exception e){
			tx.rollback();
			return null;
		}finally{
			this.closeEntityManager();
		}
	}
}
