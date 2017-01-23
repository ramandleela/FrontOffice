package frontoffice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bookstore.frontoffice.Application;
import com.bookstore.frontoffice.component.FrontOfficeComponent;

public class TestService {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
    /*
    	Inject WebApplication Context
    */
    @Autowired
    private WebApplicationContext webContext;
    @Autowired
	private FrontOfficeComponent frontOfficeComponent;
    private MockMvc mockMvc;

	/*
	   This method should executed Before any test methods
    */
	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	// Presentation Layer

	@Test
	public void homePage() throws Exception {
	
	// As static imports are defined code can can be compacted
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			;
	}

	@Test
	public void booksPage() throws Exception {
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			;
	}
	
	@Test
	public void getAbookPage() throws Exception {
		mockMvc.perform(get("/1"))
			.andExpect(status().isOk())
			;
	}
	
	@Test
	public void editPage() throws Exception {
		mockMvc.perform(get("edit/1"))
			.andExpect(status().isOk())
			;
	}
	
	
	@Test
	public void createBook() throws Exception {
		mockMvc.perform(get("/create"))
			.andExpect(status().isOk())
			;
	}

	@Test
	public void saveBook() throws Exception {
		mockMvc.perform(post("savebook"))
			.andExpect(status().isOk())
			;
	}
	
	@Test
	public void deleteBook() throws Exception {
		mockMvc.perform(post("/delete/1"))
			.andExpect(status().isOk())
			;
	}


	// Service Layer
	@Test
	public void testService() {
		logger.info("Starting Test Service"); // testService does not write to report/test/index.html
		frontOfficeComponent.initialize(); 
		logger.info("End of Test Service");
	}

	
}
