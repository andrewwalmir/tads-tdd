package ifpr.models;

import java.util.Date;
import java.util.List;

public class Locacao {

    private List<Filme> filmes;
    private Usuario usuario;
    private Date dataLocacao;
    private Date dataDevolucao;
    private Double valor;

    public Locacao(){}

    public Locacao(List<Filme> filmes, Usuario usuario, Date dataLocacao, Date dataDevolucao, Double valor) {
        this.filmes = filmes;
        this.usuario = usuario;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValor() {
        return this.valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
