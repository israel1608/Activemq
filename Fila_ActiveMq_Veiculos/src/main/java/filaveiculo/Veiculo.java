/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filaveiculo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Date;
/**
 *
 * @author flavia
 */
@XStreamAlias("Veiculo")
public class Veiculo{
    String nomeCliente;
    String marcaModeloVeiculo;
    int  anoModelo;
    double valorVenda;
    Date dataPublicacao; // valor default é a data corrente de inserção   

    public Veiculo(String nomeCliente, String marcaModeloVeiculo, int anoModelo, double valorVenda,Date dataPublicacao) {
        this.nomeCliente = nomeCliente;
        this.marcaModeloVeiculo = marcaModeloVeiculo;
        this.anoModelo = anoModelo;
        this.valorVenda = valorVenda;
        this.dataPublicacao = dataPublicacao;
    }
    
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getMarcaModeloVeiculo() {
        return marcaModeloVeiculo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setMarcaModeloVeiculo(String marcaModeloVeiculo) {
        this.marcaModeloVeiculo = marcaModeloVeiculo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setDataPublicacao(Date dataPublicacao) {
       this.dataPublicacao = dataPublicacao;
    }
     
}

