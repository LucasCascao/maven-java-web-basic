package br.com.alura.lojaweb.service;

import br.com.alura.lojaweb.domain.Produto;
import br.com.alura.lojaweb.util.RequestHelper;
import br.com.alura.lojaweb.util.ResultadoRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoService {

    private static final List<Produto> PRODUTOS = new ArrayList<>();

    public ResultadoRequest<Produto> buscaProdutos() throws IOException {
        ResultadoRequest<Produto> resultado = new ResultadoRequest<>();
        resultado.setResultados(PRODUTOS);
        return resultado;
    }

    public ResultadoRequest<Produto> inseri(Produto novoProduto) throws IOException {
        PRODUTOS.add(novoProduto);
        ResultadoRequest<Produto> resultado = new ResultadoRequest<>();
        resultado.setMensagem("Inserido com sucesso");
        return resultado;
    }

    public ResultadoRequest<Produto> altera(Produto produtoRecebido) throws IOException {
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

        return resultado;
    }

    public ResultadoRequest<Produto> deleta(Produto produtoRecebido) throws IOException {
        Produto produtoAExcluir = PRODUTOS.stream()
                .filter(produto -> produto.getNome().equals(produtoRecebido.getNome()))
                .peek(produto -> produto.setValor(produtoRecebido.getValor())).findFirst().orElse(null);
        PRODUTOS.remove(produtoAExcluir);
        ResultadoRequest<Produto> resultado = new ResultadoRequest<>();
        resultado.setMensagem("Produto excluido com sucesso");
        return resultado;
    }

}
