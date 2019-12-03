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
        Double valorUltimoFime = ultimoFilme.getPreco();

        Double precoTotal = 0.0;
        int x = 0;

        for (Filme filme : filmes) {
            // Testar estoque
            if (filme.getEstoque() == 0) {
                throw new Exception("filme sem estoque");
            }

            // Calcular valor da locação
            precoTotal += filme.getPreco();

            // Quantidade de Filmes
            x++;
        }

        Double valorFinal = 0.0;
        Double valorComDesconto = 0.0;

        switch (x) {
            case 3:
                // 25% no terceiro Filme;
                valorFinal = precoTotal - valorUltimoFime;
                valorComDesconto = valorUltimoFime * 0.75;
                valorFinal = valorFinal + valorComDesconto;
                break;
            case 4:
                // 50% no quarto Filme;
                valorFinal = precoTotal - valorUltimoFime;
                valorComDesconto = valorUltimoFime * 0.50;
                valorFinal = valorFinal + valorComDesconto;
                break;
            case 5:
                // 75% no quinto Filme;
                valorFinal = precoTotal - valorUltimoFime;
                valorComDesconto = valorUltimoFime * 0.25;
                valorFinal = valorFinal + valorComDesconto;
                break;
            case 6:
                // 100% no sexto Filme;
                valorFinal = precoTotal - valorUltimoFime;
                break;
            default:
                valorFinal = precoTotal;
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setFilmes(filmes);
        locacao.setDataLocacao(new Date());
        locacao.setDataDevolucao(DataUtils.adicionarDias(new Date(), 1));
        locacao.setValor(valorFinal);

        //persistir

        return locacao;
    }

}
