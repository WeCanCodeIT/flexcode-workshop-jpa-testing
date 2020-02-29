package org.wcci.books;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class JpaWiringTest {
	@Resource
	private CampusRepository campusRepo;
	@Resource
	private BookRepository bookRepo;
	@Resource
	TestEntityManager entityManager;

	@Test
	public void retrievedCampusIsTheSameAsOriginalCampus() {
		Campus testCampus = new Campus("Testervilee");
		testCampus = campusRepo.save(testCampus);

		entityManager.flush();
		entityManager.clear();

		Campus retrievedCampus = campusRepo.findById(testCampus.getId()).get();
		
		assertThat(retrievedCampus, equalTo(testCampus));
	}
	@Test
	public void retrievedCampusHasAListOfBooks() {
		Campus testCampus = new Campus("Testervilee");
		testCampus = campusRepo.save(testCampus);
		
		Book testBook1= new Book("Test Book 1", testCampus);
		testBook1 = bookRepo.save(testBook1);
		Book testBook2= new Book("Test Book 2", testCampus);
		testBook2 = bookRepo.save(testBook2);
		
		entityManager.flush();
		entityManager.clear();
		
		Campus retrievedCampus = campusRepo.findById(testCampus.getId()).get();
		assertThat(retrievedCampus.getBooks(), hasItems(testBook1, testBook2));
	}

}
