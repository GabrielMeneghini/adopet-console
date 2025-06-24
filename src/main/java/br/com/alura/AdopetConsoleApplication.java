package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                exibirMenu();

                String textoDigitado = new Scanner(System.in).nextLine();
                if(!textoDigitado.matches("^\\d+$")) textoDigitado = "0";
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                switch(opcaoEscolhida) {
                    case 1 -> commandExecutor.executeCommand(new ListarAbrigoCommand());
                    case 2 -> commandExecutor.executeCommand(new CadastrarAbrigoCommand());
                    case 3 -> commandExecutor.executeCommand(new ListarPetsDoAbrigoCommand());
                    case 4 -> commandExecutor.executeCommand(new ImportarPetsDoAbrigoCommand());
                    case 5 -> System.out.println("Finalizando o programa...");
                    default -> {System.out.println("CARACTERE INVÁLIDO!"); opcaoEscolhida = 0;}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu() {
        System.out.println("""
                        \nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:
                        1 -> Listar abrigos cadastrados
                        2 -> Cadastrar novo abrigo
                        3 -> Listar pets do abrigo
                        4 -> Importar pets do abrigo
                        5 -> Sair""");
    }
}
