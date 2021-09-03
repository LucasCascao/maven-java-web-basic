package br.com.alura.lojaweb;

import br.com.alura.lojaweb.domain.Produto;
import org.junit.Assert;
import org.junit.Test;

public class TesteProduto {

    @Test
    public void verificaProdutoComParametrosNoContrutor(){
        Produto produto = new Produto("Jujuba",1D);
        Assert.assertEquals(1D, produto.getValor(), 0.0001);
        Assert.assertEquals("Jujuba", produto.getNome());
    }

    @Test
    public void verificaProduto(){
        Produto produto = new Produto();
        produto.setNome("Jujuba");
        produto.setValor(1D);
        Assert.assertEquals(1D, produto.getValor(), 0.0001);
        Assert.assertEquals("Jujuba", produto.getNome());
    }
}
