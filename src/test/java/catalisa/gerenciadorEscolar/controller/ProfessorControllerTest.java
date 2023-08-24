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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Test
    @DisplayName("Listar todos os professores")
    public void testListarProfessores() throws Exception {
        ProfessorModel professorModel1 = new ProfessorModel();
        professorModel1.setNome("Professor1");
        professorModel1.setCurso("Curso1");

        ProfessorModel professorModel2 = new ProfessorModel();
        professorModel2.setNome("Professor2");
        professorModel2.setCurso("Curso2");

        Mockito.when(professorService.listarProfessores()).thenReturn(List.of(professorModel1, professorModel2));

        mockMvc.perform(get("/professor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Professor1"))
                .andExpect(jsonPath("$[0].curso").value("Curso1"))
                .andExpect(jsonPath("$[1].nome").value("Professor2"))
                .andExpect(jsonPath("$[1].curso").value("Curso2"));
    }

    @Test
    @DisplayName("Deletar professor por id")
    public void testDeletarProfessorPorID() throws Exception {
        Long professorId = 1L;

        mockMvc.perform(delete("/professor/{id}", professorId))
                .andExpect(status().isNoContent());

        verify(professorService, times(1)).deletarProfessor(professorId);
    }
}
