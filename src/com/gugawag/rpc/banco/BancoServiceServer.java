package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

//crie os métodos> Cadastro de nova conta
//Pesquisa de conta
//Remoção de conta


public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    @Override
    public String  cadastrarConta(String novaConta, Double saldoInicial){
        if (saldoContas.containsKey(novaConta)) {
            return "Conta já existe!";
        }
        saldoContas.put(novaConta, saldoInicial);
        return "Conta cadastrada com sucesso!";
    }
    @Override
    public String pesquisarConta(int contaBusca){
        String contaStr = String.valueOf(contaBusca);
        if (saldoContas.containsKey(contaStr)) {
            double saldo = saldoContas.get(contaStr);
            return "Conta encontrada! Saldo: " + saldo;
        }
        return "Conta não encontrada!";
    }
    
    @Override
    public String removerConta(int contaRemover) {
        String contaRemoverStr = String.valueOf(contaRemover);
        if (saldoContas.containsKey(contaRemoverStr)) {
            saldoContas.remove(contaRemoverStr);
            return "Conta removida com sucesso!";
        }
        return "Conta não encontrada!";
    }

}
