package ch.cern.todo;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.cern.todo.model.Task;
import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class TodoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskCategoryRepository taskCategoryRepository;



	@Test
	public void testGetTask() throws Exception {
		TaskCategory category = new TaskCategory(null, "Test category", "This is a test category", null);
		taskCategoryRepository.save(category);

		Task task = new Task(null, "Test task", "This is a test task", LocalDateTime.now().plusDays(1), null);
		task.setTaskCategory(category);
		task = taskRepository.save(task);

		mvc.perform(get("/tasks/" + task.getTaskId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.taskName", is("Test task")))
				.andExpect(jsonPath("$.taskDescription", is("This is a test task")))
				.andExpect(jsonPath("$.taskCategory.categoryName", is("Test category")));
	}
	@Test
	public void testCreateTask() throws Exception {
		TaskCategory category = new TaskCategory(null, "Test category", "This is a test category", null);
		taskCategoryRepository.save(category);

		Task task = new Task(null, "Test task", "This is a test task", LocalDateTime.now().plusDays(1), null);
		task.setTaskCategory(category);
		mvc.perform(post("/tasks/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(task)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.taskName", is("Test task")))
				.andExpect(jsonPath("$.taskDescription", is("This is a test task")))
				.andExpect(jsonPath("$.taskCategory.categoryName", is("Test category")));
	}

	@Test
	void testUpdateTask() throws Exception {
		TaskCategory category = new TaskCategory(null, "Test category", "This is a test category", null);
		taskCategoryRepository.save(category);

		Task task = new Task(null, "Test task", "This is a test task", LocalDateTime.now(), category);
		task = taskRepository.save(task);

		task.setTaskName("Updated task name");
		task.setTaskDescription("Updated task description");
		task.setDeadline(LocalDateTime.of(2022, 11, 11, 1, 1, 1));

		mvc.perform(put("/tasks/update/" + task.getTaskId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(task))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		Task updatedTask = taskRepository.findById(task.getTaskId()).get();
		assertEquals(task.getTaskName(), updatedTask.getTaskName());
		assertEquals(task.getTaskDescription(), updatedTask.getTaskDescription());
		assertEquals(task.getDeadline(), updatedTask.getDeadline());
	}


	@Test
	void testDeleteTask() throws Exception {
		TaskCategory category = new TaskCategory(null, "Test category", "This is a test category", null);
		taskCategoryRepository.save(category);

		Task task = new Task(null, "Test task", "This is a test task", LocalDateTime.now(), category);
		task = taskRepository.save(task);

		mvc.perform(delete("/tasks/delete/" + task.getTaskId()))
				.andExpect(status().isOk());

		List<Task> tasks = (List<Task>) taskRepository.findAll();
		assertEquals(0, tasks.size());
	}

}
