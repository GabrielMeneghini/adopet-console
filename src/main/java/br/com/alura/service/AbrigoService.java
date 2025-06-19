package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    private ClientHttpConfiguration clientHttpConfiguration;

    public AbrigoService(ClientHttpConfiguration clientHttpConfiguration) {
        this.clientHttpConfiguration = clientHttpConfiguration;
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome, telefone, email);

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHttpConfiguration.dispararRequisicaoPost(uri, abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        } else {
            System.out.println("Erro " + statusCode);
        }
    }

    public void listarAbrigosCadastrados() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = clientHttpConfiguration.dispararRequisicaoGet(uri);

        String responseBody = response.body();
        Type listType = new TypeToken<List<Abrigo>>() {}.getType();
        List<Abrigo> abrigos = new Gson().fromJson(responseBody, listType);

        if(!abrigos.isEmpty()) {
            mostrarAbrigos(abrigos);
        } else {
            System.out.println("Não há abrigos cadastrados.");
        }
    }

    public void mostrarAbrigos(List<Abrigo> abrigos) {
        System.out.println("Abrigos cadastrados:");
        for(Abrigo abrigo: abrigos) {
            System.out.println(abrigo.getId() + " - " + abrigo.getNome());
        }
    }

}
