package br.com.alura.lojaweb.controller.produto.v2;

import br.com.alura.lojaweb.domain.Produto;
import br.com.alura.lojaweb.service.ProdutoService;
import br.com.alura.lojaweb.util.RequestHelper;
import br.com.alura.lojaweb.util.ResultadoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/v2/produtos"})
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<ResultadoRequest<Produto>> get() throws IOException {
        return ResponseEntity.ok().body(service.buscaProdutos());
    }

    @PostMapping
    protected ResponseEntity<ResultadoRequest<Produto>> post(@RequestBody Produto produto) throws IOException {
        return ResponseEntity.ok().body(service.inseri(produto));
    }

    @PutMapping
    protected ResponseEntity<ResultadoRequest<Produto>> put(@RequestBody Produto produto) throws IOException {
        return ResponseEntity.ok().body(service.altera(produto));
    }

    @DeleteMapping
    protected ResponseEntity<ResultadoRequest<Produto>> delete(@RequestBody Produto produto) throws IOException {
        return ResponseEntity.ok().body(service.deleta(produto));
    }
}
