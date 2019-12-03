package ifpr.services;

import ifpr.exceptions.NaoPodeTerEstoqueVazioException;
import ifpr.models.Filme;
import ifpr.models.Locacao;
import ifpr.models.Usuario;
import ifpr.utils.DataUtils;

import java.util.Date;
import java.util.List;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {

        // Pegar último filme da Lista
        Filme ultimoFilme = filmes.get(filmes.size() - 1);

        // Pegar valor do último filme
        Double valorUltimoFilme = ultimoFilme.getPreco();

        Double precoTotal = 0.0;
        int qtdFilmes = 0;

        for (Filme filme : filmes) {
            // Testar estoque
            if (filme.getEstoque() == 0) {
                throw new Exception("filme sem estoque");
            }

            // Calcular valor da locação
            precoTotal += filme.getPreco();

            // Quantidade de Filmes
            qtdFilmes++;
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setFilmes(filmes);
        locacao.setDataLocacao(new Date());
        locacao.setDataDevolucao(DataUtils.adicionarDias(new Date(), 1));
        // Chama o método Valor Total da Locação, que calcula os descontos, caso haja
        locacao.setValor(ValorTotalLocacao(qtdFilmes, precoTotal, valorUltimoFilme));

        //persistir

        return locacao;
    }
    public Double ValorTotalLocacao(int qtdFilmes, Double precoTotal, Double valorUltimoFilme){
        Double valorFinal = 0.0;
        Double valorComDesconto = 0.0;

        switch (qtdFilmes) {
            case 3:
                // 25% no terceiro Filme;
                valorFinal = precoTotal - valorUltimoFilme;
                valorComDesconto = valorUltimoFilme * 0.75;
                valorFinal = valorFinal + valorComDesconto;
                break;
            case 4:
                // 50% no quarto Filme;
                valorFinal = precoTotal - valorUltimoFilme;
                valorComDesconto = valorUltimoFilme * 0.50;
                valorFinal = valorFinal + valorComDesconto;
                break;
            case 5:
                // 75% no quinto Filme;
                valorFinal = precoTotal - valorUltimoFilme;
                valorComDesconto = valorUltimoFilme * 0.25;
                valorFinal = valorFinal + valorComDesconto;
                break;
            case 6:
                // 100% no sexto Filme;
                valorFinal = precoTotal - valorUltimoFilme;
                break;
            default:
                valorFinal = precoTotal;
        }

        return valorFinal;
    }
}
