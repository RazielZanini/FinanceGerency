package model;

import java.time.LocalDate;

import utils.enums.Classifications;

public class EntradaFinanceira {

    private String Nome;
    private Classifications Classificacao;
    private double Valor;
    private String DataEntrada;
    private LocalDate DataCriacao;
    private String TipoEntrada;

    public EntradaFinanceira(){
    }
    
    public EntradaFinanceira(String nome, Classifications classificacao, double valor, String dataEntrada, LocalDate dataCriacao, String tipoEntrada){
        this.Nome = nome;
        this.Classificacao = classificacao;
        this.Valor = valor;
        this.DataEntrada = dataEntrada;
        this.DataCriacao = dataCriacao;
        this.TipoEntrada = tipoEntrada;
    }

    

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome){
        this.Nome = nome;
    }

    public Classifications getClassificacao(){
        return Classificacao;
    }

    public void setClassificacao(Classifications classificacao) {
        this.Classificacao = classificacao;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public String getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        DataEntrada = dataEntrada;
    }

    public LocalDate getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public String getTipoEntrada() {
        return TipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.TipoEntrada = tipoEntrada;
    }

    @Override
    public String toString(){
        return "Dados da Entrada Financeira " + Nome + ":\n "
        +"Classificacao: "+Classificacao + "\n"
        +"Valor: " + Valor + "\n"
        +"Data da entrada: "+DataEntrada+ "\n"
        +"Data criacao: " + DataCriacao;
    }

}
