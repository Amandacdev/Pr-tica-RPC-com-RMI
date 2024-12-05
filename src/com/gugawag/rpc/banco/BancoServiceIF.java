package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    String cadastrarConta(String novaConta, Double saldoInicial);
    String pesquisarConta(int contaBusca);
}
