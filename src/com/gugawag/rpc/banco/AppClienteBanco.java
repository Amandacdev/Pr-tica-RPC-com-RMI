package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry("192.168.0.12",1099);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("O saldo da conta é:" + banco.saldo(conta));
                    break;
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println("Quantidade de contas:" + banco.quantidadeContas());
                    break;
                }
                case 3: //criar conta
                {
                    System.out.println("Digite o número da nova conta:");
                    String novaConta = entrada.next();
                    System.out.println("Digite o saldo inicial da conta:");
                    Double saldoInicial = entrada.nextDouble();

                    String resultado = banco.cadastrarConta(novaConta,saldoInicial);

                   System.out.println(resultado);
                   
                }
                case 4: //pesquisar conta
                {
                    System.out.println("Digite o número de conta a ser buscada:");
                    int contaBusca = entrada.nextInt();
                    System.out.println(banco.pesquisarConta(contaBusca));
                    break;

                } //remoção de conta
                case 5:
                {
                    System.out.println("Digite o número da conta a ser removida:");
                    int contaRemover = entrada.nextInt();
                    System.out.println(banco.removerConta(contaRemover));
                    break;

                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("Aluna: Amanda Cruz de Araújo");
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Criar conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
    }

}
