package br.com.alura;

import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;
import br.com.alura.client.ClientHttpConfiguration;
import com.google.gson.Gson;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {

        Gson gson = new Gson();
        ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration(gson);

        PetService petService = new PetService(clientHttpConfiguration);
        AbrigoService abrigoService = new AbrigoService(clientHttpConfiguration);

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("""
                            
                        DIGITE O NÚMERO DA OPERAÇÃO DESEJADA:
                        1 -> Listar abrigos cadastrados
                        2 -> Cadastrar novo abrigo
                        3 -> Listar pets do abrigo
                        4 -> Importar pets do abrigo
                        5 -> Sair""");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    abrigoService.listarAbrigosCadastrados();
                } else if (opcaoEscolhida == 2) {
                    abrigoService.cadastrarAbrigo();
                } else if (opcaoEscolhida == 3) {
                    petService.listarPetsDoAbrigo();
                } else if (opcaoEscolhida == 4) {
                    petService.importarPetsDoAbrigo();
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
