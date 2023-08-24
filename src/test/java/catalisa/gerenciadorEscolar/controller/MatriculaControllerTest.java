package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.model.MatriculaModel;
import catalisa.gerenciadorEscolar.service.MatriculaService;
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
public class MatriculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatriculaService matriculaService;

    @Test
    @DisplayName("Cadastrar matrícula")
    public void testCadastrarMatricula() throws Exception {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setNome("Aluno1");
        alunoModel.setEmail("aluno1@teste.com");

        CursoModel cursoModel = new CursoModel();
        cursoModel.setNomeCurso("Curso1");
        cursoModel.setCargaHoraria(40);

        MatriculaModel matriculaModel = new MatriculaModel();
        matriculaModel.setAluno(alunoModel);
        matriculaModel.setCurso(cursoModel);

        Mockito.when(matriculaService.cadastrarMatricula(Mockito.any(MatriculaModel.class))).thenReturn(matriculaModel);

        mockMvc.perform(post("/matricula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"aluno\": {\"nome\":\"Aluno1\",\"email\":\"aluno1@teste.com\"},\"curso\":{\"nomeCurso\":\"Curso1\",\"cargaHoraria\":40}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aluno.nome").value("Aluno1"))
                .andExpect(jsonPath("$.curso.nomeCurso").value("Curso1"));
    }
}
