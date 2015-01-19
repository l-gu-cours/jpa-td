package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import td.entities.Author;

public class AuthorTest {

	private void log(String msg) {
		System.out.println( msg );
	}
	
	public Author save( EntityManager em, Author author ) {		
		log("save("+author+")");
		em.getTransaction().begin();
		Author entity = em.merge(author);
		em.getTransaction().commit();
		return entity ;
	}
	
	private void delete( EntityManager em, int id ) {
		log( "delete : id = " + id );
		Author author = em.find(Author.class, id);
		if ( author != null ) {
			log("exists => remove");
			em.getTransaction().begin();		
			em.remove(author);		
			em.getTransaction().commit();
		}
		else {
			log("doesn't exist");
		}
	}
	
	public Author findAuthor(EntityManager em, int id ) {
		log( "find : id = " + id );
		Author author = em.find(Author.class, id);
		if ( author != null ) {
			log( "Author #" + id + " found : " + author );
		}
		else {
			log( "Author #" + id + " not found.");
		}
		return author ;
	}
	
	@Test
	public void test() {
		log("--- Persistence.createEntityManagerFactory(xx)...");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("td"); 
		
		log("--- emf.createEntityManager()...");		
		EntityManager em = emf.createEntityManager(); 
		
		assertNotNull(em);
		log("----------");
		log("--- Clean : delete 100 & 200 ");
		delete(em, 100);
		delete(em, 200);
		
		log("--- Init : save 100 ");
		Author author = new Author();
		author.setId(100);
		author.setFirstName("Bart");
		author.setLastName("Simpson");
		
		Author authorSaved = save(em, author);
		assertNotNull(authorSaved);
		assertTrue(authorSaved != author);
		
		log("--- Find : 100 ");
		Author author100 = findAuthor(em, 100);
		assertNotNull(author100);
		assertEquals(author.getFirstName(), author100.getFirstName());
		assertEquals(author.getLastName(), author100.getLastName());

		Author author200 = findAuthor(em, 200);
		assertNull(author200);
		
		System.out.println("----------");
		
		System.out.println("--- closing ...");		
		
        em.close(); 
        emf.close();			
	}

}
