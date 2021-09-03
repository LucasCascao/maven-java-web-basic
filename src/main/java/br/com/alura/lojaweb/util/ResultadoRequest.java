package br.com.alura.lojaweb.util;

import java.util.List;

public class ResultadoRequest<T> {

    private String mensagem;
    private List<T> resultados;

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<T> getResultados() {
        return resultados;
    }
}
