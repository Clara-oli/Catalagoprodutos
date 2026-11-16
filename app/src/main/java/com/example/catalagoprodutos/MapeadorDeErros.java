package com.example.catalagoprodutos;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
public class MapeadorDeErros {
    public static String interpretarCodigoHttp(int statusCode) {
        switch (statusCode) {
            case 400: return "Requisição malformada (400). Verifique os parâmetros enviados.";
            case 401: return "Sessão não autorizada (401). Realize o login novamente.";
            case 403: return "Acesso proibido (403). Você não possui permissão para este recurso.";
            case 404: return "Recurso não encontrado no servidor (404).";
            case 500: return "Erro interno no servidor (500). Tente novamente em alguns instantes.";
            case 503: return "Servidor indisponível ou em manutenção (503).";
            default: return "O servidor respondeu com falha. Código HTTP: " + statusCode;
        }
    }
    public static String interpretarExcecaoRede(Throwable t) {
        if (t instanceof UnknownHostException) {
            return "Sem conexão com a internet. Verifique seu Wi-Fi ou dados móveis.";
        } else if (t instanceof SocketTimeoutException) {
            return "Tempo limite de conexão esgotado (Timeout). Tente novamente.";
        } else if (t instanceof JsonSyntaxException) {
            return "Erro ao processar resposta do servidor (Falha no Parsing JSON).";
        } else if (t instanceof IOException) {
            return "Falha na transmissão de dados pela rede: " + t.getLocalizedMessage();
        } else {
            return "Ocorreu um erro inesperado: " + t.getMessage();
        }
    }
}