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
}
