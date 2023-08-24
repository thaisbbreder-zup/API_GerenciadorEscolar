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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    @DisplayName("Listar todas as matrículas")
    public void testListarMatriculas() throws Exception {
        MatriculaModel matriculaModel1 = new MatriculaModel();
        matriculaModel1.setAluno(new AlunoModel());
        matriculaModel1.getAluno().setNome("Aluno1");
        matriculaModel1.setCurso(new CursoModel());
        matriculaModel1.getCurso().setNomeCurso("Curso1");
        matriculaModel1.setDataMatricula("2023-08-17");

        MatriculaModel matriculaModel2 = new MatriculaModel();
        matriculaModel2.setAluno(new AlunoModel());
        matriculaModel2.getAluno().setNome("Aluno2");
        matriculaModel2.setCurso(new CursoModel());
        matriculaModel2.getCurso().setNomeCurso("Curso2");
        matriculaModel2.setDataMatricula("2023-08-18");

        List<MatriculaModel> matriculas = new ArrayList<>();
        matriculas.add(matriculaModel1);
        matriculas.add(matriculaModel2);

        Mockito.when(matriculaService.listarMatriculas()).thenReturn(matriculas);

        mockMvc.perform(get("/matricula"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aluno").value("Aluno1"))
                .andExpect(jsonPath("$[0].curso").value("Curso1"))
                .andExpect(jsonPath("$[0].dataMatricula").value("2023-08-17"))
                .andExpect(jsonPath("$[1].aluno").value("Aluno2"))
                .andExpect(jsonPath("$[1].curso").value("Curso2"))
                .andExpect(jsonPath("$[1].dataMatricula").value("2023-08-18"));
    }

    //atualizar curso

    //deletar matricula por id
   /* @Test
    @DisplayName("Deletar matricula por id")
    public void testDeletarMatriculaPorID() throws Exception {
        Long alunoId = 1L;

        mockMvc.perform(delete("/matricula/{id}", alunoId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Matricula excluída com sucesso!"));

        verify(matriculaService, times(1)).deletarMatricula(alunoId);
    }*/
}
