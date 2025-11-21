package br.com.fiap.service;

import br.com.fiap.model.entity.Portfolio;
import br.com.fiap.model.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository repository;

    public List<Portfolio> listarTodos() {
        return repository.findAll();
    }

    public Portfolio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Portfolio salvar(Portfolio portfolio) {
        return repository.save(portfolio);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}