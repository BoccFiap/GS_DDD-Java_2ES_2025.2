package br.com.fiap.model.entity;

import br.com.fiap.model.dto.PortfolioDTO;
import br.com.fiap.model.enums.StatusPortfolio;
import br.com.fiap.model.enums.Trilha;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ponteia_portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCandidato;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Trilha trilha; // ex: ANALISE_DADOS_JR

    private String linkProjeto1;
    private String linkProjeto2;
    @Column(length = 4000)
    private String descricaoAprendizado;

    @Enumerated(EnumType.STRING)
    private StatusPortfolio status;

    private LocalDateTime dataEnvio;
    private String feedbackIA;
    private String parecerHumano;

    // Construtores
    public Portfolio() {
        this.dataEnvio = LocalDateTime.now();
        this.status = StatusPortfolio.AGUARDANDO_ANALISE;
    }

    public Portfolio(PortfolioDTO dto) {
        this();
        this.nomeCandidato = dto.nomeCandidato();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.trilha = dto.trilha();
        this.linkProjeto1 = dto.linkProjeto1();
        this.linkProjeto2 = dto.linkProjeto2();
        this.descricaoAprendizado = dto.descricaoAprendizado();
    }

    // ===== GETTERS E SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Trilha getTrilha() {
        return trilha;
    }

    public void setTrilha(Trilha trilha) {
        this.trilha = trilha;
    }

    public String getLinkProjeto1() {
        return linkProjeto1;
    }

    public void setLinkProjeto1(String linkProjeto1) {
        this.linkProjeto1 = linkProjeto1;
    }

    public String getLinkProjeto2() {
        return linkProjeto2;
    }

    public void setLinkProjeto2(String linkProjeto2) {
        this.linkProjeto2 = linkProjeto2;
    }

    public String getDescricaoAprendizado() {
        return descricaoAprendizado;
    }

    public void setDescricaoAprendizado(String descricaoAprendizado) {
        this.descricaoAprendizado = descricaoAprendizado;
    }

    public StatusPortfolio getStatus() {
        return status;
    }

    public void setStatus(StatusPortfolio status) {
        this.status = status;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getFeedbackIA() {
        return feedbackIA;
    }

    public void setFeedbackIA(String feedbackIA) {
        this.feedbackIA = feedbackIA;
    }

    public String getParecerHumano() {
        return parecerHumano;
    }

    public void setParecerHumano(String parecerHumano) {
        this.parecerHumano = parecerHumano;
    }
}