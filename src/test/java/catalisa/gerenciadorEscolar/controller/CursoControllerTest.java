package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.model.CursoModel;
import catalisa.gerenciadorEscolar.service.CursoService;
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
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Test
    @DisplayName("Cadastrar curso")
    public void testCadastrarCurso() throws Exception {
        CursoModel cursoModel = new CursoModel();
        cursoModel.setNomeCurso("Curso1");
        cursoModel.setCargaHoraria(40);

        Mockito.when(cursoService.cadastrarCurso(Mockito.any(CursoModel.class))).thenReturn(cursoModel);

        mockMvc.perform(post("/curso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomeCurso\":\"Curso1\",\"cargaHoraria\":40}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCurso").value("Curso1"))
                .andExpect(jsonPath("$.cargaHoraria").value(40));
    }

    @Test
    @DisplayName("Listar todos os cursos cadastrados")
    public void testListarCursos() throws Exception {
        CursoModel cursoModel1 = new CursoModel();
        cursoModel1.setNomeCurso("Curso1");
        cursoModel1.setCargaHoraria(4000);

        CursoModel cursoModel2 = new CursoModel();
        cursoModel2.setNomeCurso("Curso2");
        cursoModel2.setCargaHoraria(5000);

        List<CursoModel> cursos = new ArrayList<>();
        cursos.add(cursoModel1);
        cursos.add(cursoModel2);

        Mockito.when(cursoService.listarCursos()).thenReturn(cursos);

        mockMvc.perform(get("/curso"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCurso").value("Curso1"))
                .andExpect(jsonPath("$[0].cargaHoraria").value(4000))
                .andExpect(jsonPath("$[1].nomeCurso").value("Curso2"))
                .andExpect(jsonPath("$[1].cargaHoraria").value(5000));

        verify(cursoService, times(1)).listarCursos();
    }

    @Test
    @DisplayName("Buscar curso por id cadastrado")
    public void testBuscarCursoPorID() throws Exception {
        CursoModel cursoModel = new CursoModel();
        cursoModel.setId(1L);
        cursoModel.setNomeCurso("Curso1");
        cursoModel.setCargaHoraria(4000);

        Mockito.when(cursoService.buscarCursoPorId(1L)).thenReturn(Optional.of(cursoModel));

        mockMvc.perform(get("/curso/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeCurso").value("Curso1"))
                .andExpect(jsonPath("$.cargaHoraria").value(4000));
    }

    @Test
    @DisplayName("Deletar curso por id")
    public void testDeletarCursoPorID() throws Exception {
        Long cursoId = 1L;

        mockMvc.perform(delete("/curso/{id}", cursoId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Curso excluído com sucesso!"));
        //verifica se a mensagem "Curso excluído com sucesso!" foi retornada após a exclusão do curso

        verify(cursoService, times(1)).deletarCurso(cursoId);
    }
}
