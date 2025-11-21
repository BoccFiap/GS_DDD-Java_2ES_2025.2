package br.com.fiap.model.dto;

import br.com.fiap.model.enums.Trilha;
import jakarta.validation.constraints.*;

public record PortfolioDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nomeCandidato,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull(message = "Selecione uma trilha")
        Trilha trilha,

        @NotBlank(message = "Link do projeto 1 é obrigatório")
        String linkProjeto1,

        @NotBlank(message = "Link do projeto 2 é obrigatório")
        String linkProjeto2,

        @NotBlank(message = "Descreva seu aprendizado")
        @Size(min = 100, max = 2000, message = "A descrição deve ter entre 100 e 2000 caracteres")
        String descricaoAprendizado

) {}