package td.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import td.entities.Author;

public class TestAuthorFind {

	public static void main(String[] args) 
	{
		System.out.println("--- Persistence.createEntityManagerFactory(xx)...");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("td"); 
		
		System.out.println("--- emf.createEntityManager()...");		
		EntityManager em = emf.createEntityManager(); 
		
		System.out.println("----------");

		findAuthor(em, 4);

		findAuthor(em, 27);
		
		System.out.println("----------");
		
		System.out.println("--- closing ...");		
		
        em.close(); 
        emf.close();			
	}

	 public static Author findAuthor(EntityManager em , int id ) 
	 {
		Author author = em.find(Author.class, id);
		if ( author != null ) {
			System.out.println( "Author #" + id + " found : " + author );
		}
		else {
			System.out.println( "Author #" + id + " not found.");
		}
		return author ;
	 }

}
