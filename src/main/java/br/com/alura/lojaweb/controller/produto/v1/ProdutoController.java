package br.com.alura.lojaweb.controller.produto.v1;

import br.com.alura.lojaweb.domain.Produto;
import br.com.alura.lojaweb.util.RequestHelper;
import br.com.alura.lojaweb.util.ResultadoRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/v1/produtos"})
public class ProdutoController extends HttpServlet {

    private static final List<Produto> PRODUTOS = new ArrayList<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(RequestHelper.parseToJson(PRODUTOS));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Produto novoProduto = RequestHelper.parseToObject(req, Produto.class);
        PRODUTOS.add(novoProduto);
        resp.getWriter().write(RequestHelper.parseToJson(novoProduto));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Produto produtoRecebido = RequestHelper.parseToObject(req, Produto.class);
        Produto produtoAlterado = PRODUTOS.stream()
                .filter(produto -> produto.getNome().equals(produtoRecebido.getNome()))
                .peek(produto -> produto.setValor(produtoRecebido.getValor())).findFirst().orElse(null);

        ResultadoRequest<Produto> resultado = new ResultadoRequest<>();

        if(produtoAlterado != null){
            PRODUTOS.set(PRODUTOS.indexOf(produtoAlterado), produtoAlterado);
            resultado.setResultados(PRODUTOS);
        } else {
            resultado.setMensagem("Produto não econtrado");
        }

        resp.getWriter().write(RequestHelper.parseToJson(resultado));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Produto produtoRecebido = RequestHelper.parseToObject(req, Produto.class);
        Produto produtoAExcluir = PRODUTOS.stream()
                .filter(produto -> produto.getNome().equals(produtoRecebido.getNome()))
                .peek(produto -> produto.setValor(produtoRecebido.getValor())).findFirst().orElse(null);
        PRODUTOS.remove(produtoAExcluir);
    }
}
