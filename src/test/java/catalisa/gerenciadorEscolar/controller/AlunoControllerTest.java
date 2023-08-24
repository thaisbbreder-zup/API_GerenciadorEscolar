package catalisa.gerenciadorEscolar.controller;

import catalisa.gerenciadorEscolar.model.AlunoModel;
import catalisa.gerenciadorEscolar.service.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Test
    @DisplayName("Cadastrar alunos")
    public void testCadastrarAlunos() throws Exception {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setNome("Aluno1");
        alunoModel.setEmail("aluno1@teste.com");

        Mockito.when(alunoService.cadastrarAluno(Mockito.any(AlunoModel.class))).thenReturn(alunoModel);

        mockMvc.perform(post("/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Aluno1\",\"email\":\"aluno1@teste.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Aluno1"))
                .andExpect(jsonPath("$.email").value("aluno1@teste.com"));
    }

    @Test
    @DisplayName("Listar todos os alunos cadastrados")
    public void testListarAlunos() throws Exception {
        AlunoModel alunoModel1 = new AlunoModel();
        alunoModel1.setNome("Aluno1");
        alunoModel1.setEmail("aluno1@teste.com");

        AlunoModel alunoModel2 = new AlunoModel();
        alunoModel2.setNome("Aluno2");
        alunoModel2.setEmail("aluno2@teste.com");

        List<AlunoModel> alunos = new ArrayList<>();
        alunos.add(alunoModel1);
        alunos.add(alunoModel2);

        Mockito.when(alunoService.listarAlunos()).thenReturn(alunos);

        mockMvc.perform(get("/aluno"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Aluno1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("aluno1@teste.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Aluno2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("aluno2@teste.com"));

        verify(alunoService, times(1)).listarAlunos();
    }

    @Test
    @DisplayName("Buscar aluno por id cadastrado")
    public void testBuscarAlunoPorID() throws Exception {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(1L);
        alunoModel.setNome("Aluno1");
        alunoModel.setIdade(26);
        alunoModel.setEmail("aluno1@teste.com");

        Mockito.when(alunoService.buscarAlunoPorId(1L)).thenReturn(Optional.of(alunoModel));

        mockMvc.perform(get("/aluno/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Aluno1"))
                .andExpect(jsonPath("$.idade").value(26))
                .andExpect(jsonPath("$.email").value("aluno1@teste.com"));
    }

    @Test
    @DisplayName("Deletar aluno por id")
    public void testDeletarAlunoPorID() throws Exception {
        Long alunoId = 1L;

        mockMvc.perform(delete("/aluno/{id}", alunoId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Aluno(a) excluído(a) com sucesso!"));
        //verifica se a mensagem "Aluno(a) excluído(a) com sucesso!" foi retornada após a exclusão do aluno

        verify(alunoService, times(1)).deletarAluno(alunoId);
    }


}

