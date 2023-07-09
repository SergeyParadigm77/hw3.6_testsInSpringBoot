package ru.hogwarts.school;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import java.util.Optional;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.web.servlet.function.ServerResponse.status;

@WebMvcTest
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;
    @Test
    public void createFacultyTest() throws Exception {
        final Long id  = 5L;
        final String name = "TestFaculty";
        final String color = "Black";
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("Long id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty facultyTest = new Faculty();
        facultyTest.setId(id);
        facultyTest.setName(name);
        facultyTest.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyTest);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(facultyTest));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculties")
                .content(facultyObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOK())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOK())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

}
