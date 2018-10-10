package spring.in.action;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spring.in.action.mwc.basic.controller.HomeController;
import spring.in.action.mwc.basic.controller.SpittleController;
import spring.in.action.mwc.basic.model.Spittle;
import spring.in.action.mwc.basic.service.SpittleRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		assertEquals("home", controller.home());
	}

	@Test
	public void testHomePageAsMock() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRepository);

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();

		// 1.
		// mockMvc.perform(get("/spittles")).andExpect(view().name("spittles"))
		// .andExpect(model().attributeExists("spittleList"))
		// .andExpect(model().attribute("spittleList",
		// hasItems(expectedSpittles.toArray())));

		mockMvc.perform(get("/spittles/v2?max=238900&count=50")).andExpect(view().name("spittles/v2"))
				.andExpect(model().attributeExists("spittleList"))
		// .andExpect(model().attribute("spittleList",
		// hasItems(expectedSpittles.toArray())))
		;
	}

	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spittles/v3/12345")).andExpect(view().name("spittle"))
				.andExpect(model().attributeExists("spittle")).andExpect(model().attribute("spittle", expectedSpittle));
	}

	@Test
	public void shouldShowRegistration() throws Exception {
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spittles/register")).andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		Spittle unsaved = new Spittle("jbauer", new Date(), 0.1, 0.2);
		Spittle saved = new Spittle("jbauer", new Date(), 0.1, 0.2);
		when(mockRepository.save(unsaved)).thenReturn(saved);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/spitter/register").param("firstName", "Jack").param("lastName", "Bauer")
				.param("username", "jbauer").param("password", "24hours")).andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
}
