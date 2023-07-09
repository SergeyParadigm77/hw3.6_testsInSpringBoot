package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
        @LocalServerPort
        private int port;
        @Autowired
        private StudentController studentController;
        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        public void contextLoads() throws Exception {
                Assertions.assertNotNull(studentController);
        }

        @Test
        public void testGetAllStudents() throws Exception {
                Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/students", String.class));
        }

        @Test
        public void testGetAllByAge() throws Exception {
                Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/students/age?age=17", String.class));
        }

        @Test
        public void testPostStudent() throws Exception {
                Student studentPostTest = new Student();
                studentPostTest.setId(6L);
                studentPostTest.setName("Newt Scamander");
                studentPostTest.setAge(25);

                Assertions
                        .assertNotNull(this.restTemplate.postForObject("http://localhost:" + port + "/students", studentPostTest, String.class));
        }

        @Test
        public void testStudentInfo() throws Exception {
                Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/students/1", String.class));
        }

        @Test
        public void testStudentFindByAgeBetween() throws Exception {
                Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/students/findByAgeBetween?min=17&max=25", String.class));
        }

        @Test
        public void testGetFacultyByStudentId() throws Exception {
                Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/students/getFacultyByStudentId?id=5", String.class));
        }

        @Test
        public void testFindAllByFacultyId() throws Exception {
                Assertions.assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/students/findAllByFacultyId?id=4", String.class));
        }
}