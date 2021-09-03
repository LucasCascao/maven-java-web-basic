package br.com.alura.lojaweb.controller;

import br.com.alura.lojaweb.domain.Cliente;
import br.com.alura.lojaweb.util.RequestHelper;
import br.com.caelum.stella.tinytype.CPF;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/clientes"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cliente cliente = RequestHelper.parseToObject(req, Cliente.class);
        CPF cpf = new CPF(cliente.getCpf());

        String mensagem = null;
        if(cpf.isValido()){
            mensagem = "CPF " + cpf.getNumeroFormatado() + " é valido";
        } else {
            mensagem = "CPF " + cpf.getNumeroFormatado() + " não é valido";
        }

        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(mensagem);

    }
}
