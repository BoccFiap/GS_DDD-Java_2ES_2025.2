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
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }


    @GetMapping({"/", "/ponteia"})
    public String home() {
        return "home-ponteia";
    }


    @GetMapping("/ponteia/submeter")
    public ModelAndView formulario() {
        ModelAndView mv = new ModelAndView("submeter-portfolio");
        mv.addObject("portfolioDTO", new PortfolioDTO(null, null, null, null, null, null, null));
        return mv;
    }


    @PostMapping("/ponteia/submeter")
    public String salvar(@Valid @ModelAttribute("portfolioDTO") PortfolioDTO dto,
                         BindingResult result,
                         RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "submeter-portfolio";
        }

        service.salvar(new Portfolio(dto));
        attr.addFlashAttribute("sucesso",
                "Portfólio enviado com sucesso! Você será notificado em até 48h sobre o Selo Ponte.IA");

        return "redirect:/ponteia";
    }


    @GetMapping("/ponteia/admin")
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("admin-listagem");
        mv.addObject("portfolios", service.listarTodos());
        return mv;
    }


    @GetMapping("/ponteia/admin/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("admin-editar");
        Portfolio portfolio = service.buscarPorId(id);
        if (portfolio == null) {
            return new ModelAndView("redirect:/ponteia/admin");
        }
        mv.addObject("portfolio", portfolio);
        return mv;
    }

    @PostMapping("/ponteia/admin/editar/{id}")
    public String atualizar(@PathVariable Long id, Portfolio portfolio, RedirectAttributes attr) {
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


    @GetMapping("/ponteia/admin/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes attr) {
        service.deletar(id);
        attr.addFlashAttribute("sucesso", "Portfólio excluído com sucesso!");
        return "redirect:/ponteia/admin";
    }
}