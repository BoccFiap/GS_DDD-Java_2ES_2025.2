package br.com.fiap.controller;

import br.com.fiap.model.dto.PortfolioDTO;
import br.com.fiap.model.entity.Portfolio;
import br.com.fiap.service.PortfolioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ponteia")  // Tudo continua em /ponteia
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    // ========== RAIZ DO SITE (FUNCIONA NO RENDER.COM) ==========
    @GetMapping("/")
    public String raiz() {
        return "redirect:/ponteia";  // Redireciona a raiz para a home
    }

    // ========== HOME ==========
    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("home-ponteia");
    }

    // ================== FORMULÁRIO DE SUBMISSÃO ==================
    @GetMapping("/submeter")
    public ModelAndView formulario() {
        ModelAndView mv = new ModelAndView("submeter-portfolio");
        mv.addObject("portfolioDTO", new PortfolioDTO(null, null, null, null, null, null, null));
        return mv;
    }

    @PostMapping("/submeter")
    public ModelAndView salvar(@Valid @ModelAttribute("portfolioDTO") PortfolioDTO dto,
                               BindingResult result,
                               RedirectAttributes attr) {

        if (result.hasErrors()) {
            return new ModelAndView("submeter-portfolio")
                    .addObject("portfolioDTO", dto);
        }

        service.salvar(new Portfolio(dto));
        attr.addFlashAttribute("sucesso",
                "Portfólio enviado com sucesso! Você será notificado em até 48h sobre o Selo Ponte.IA");

        return new ModelAndView("redirect:/ponteia");
    }

    // ================== PAINEL ADMINISTRATIVO ==================
    @GetMapping("/admin")
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("admin-listagem");
        mv.addObject("portfolios", service.listarTodos());
        return mv;
    }

    // ================== EDITAR PORTFÓLIO ==================
    @GetMapping("/admin/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("admin-editar");
        Portfolio portfolio = service.buscarPorId(id);
        if (portfolio == null) {
            return new ModelAndView("redirect:/ponteia/admin");
        }
        mv.addObject("portfolio", portfolio);
        return mv;
    }

    @PostMapping("/admin/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            Portfolio portfolio,
                            RedirectAttributes attr) {

        Portfolio existente = service.buscarPorId(id);
        if (existente != null) {
            existente.setStatus(portfolio.getStatus());
            existente.setFeedbackIA(portfolio.getFeedbackIA());
            existente.setParecerHumano(portfolio.getParecerHumano());
            service.salvar(existente);
            attr.addFlashAttribute("sucesso", "Avaliação atualizada com sucesso!");
        }
        return "redirect:/ponteia/admin";
    }

    // ================== DELETAR PORTFÓLIO ==================
    @GetMapping("/admin/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attr) {
        service.deletar(id);
        attr.addFlashAttribute("sucesso", "Portfólio excluído com sucesso!");
        return "redirect:/ponteia/admin";
    }
}