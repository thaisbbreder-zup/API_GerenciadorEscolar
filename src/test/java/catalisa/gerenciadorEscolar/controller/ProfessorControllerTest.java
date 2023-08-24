package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.model.ProfessorModel;
import catalisa.gerenciadorEscolar.service.ProfessorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Test
    @DisplayName("Cadastrar professor")
    public void testCadastrarProfessor() throws Exception {
        ProfessorModel professorModel = new ProfessorModel();
        professorModel.setNome("Professor1");
        professorModel.setCurso("Curso1");
        professorModel.setIdade(30);
        professorModel.setSalario(3000);

        Mockito.when(professorService.cadastrarProfessor(Mockito.any(ProfessorModel.class))).thenReturn(professorModel);

        mockMvc.perform(post("/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Professor1\",\"curso\":\"Curso1\",\"idade\":30,\"salario\":3000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Professor1"))
                .andExpect(jsonPath("$.curso").value("Curso1"));
    }
}
