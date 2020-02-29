package org.wcci.books;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class CampusTest {
	@InjectMocks
	private CampusController underTest;
	@Mock
	private CampusRepository campusRepo;
	private Model mockModel;
	private MockMvc mockMvc;
	private List<Campus> testListOfCampuses;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockModel = mock(Model.class);
		Campus campus = new Campus("test campus");
		testListOfCampuses = Collections.singletonList(campus);
		when(campusRepo.findAll()).thenReturn(testListOfCampuses);
		mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
	}

	@Test
	public void showAllCampusesShouldReturnCampusesView() {
		String viewName = underTest.showAllCampuses(mockModel);
		assertEquals("campusesView", viewName);
	}
	@Test 
	public void showAllCampusesShouldInteractWithDependencies() {
		
		
		underTest.showAllCampuses(mockModel);
		
		verify(campusRepo).findAll();
		verify(mockModel).addAttribute("campuses",testListOfCampuses);
		
	}
	@Test
	public void showAllCampusesIsMappedCorrectly() throws Exception {
		mockMvc.perform(get("/campuses"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
