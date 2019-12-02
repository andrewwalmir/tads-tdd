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

        Double precoTotal = 0.0;

        for (Filme filme: filmes) {
            //Testar estoque
            if (filme.getEstoque() == 0) {
                throw new Exception("filme sem estoque");
            }
            //calcular o preço
            precoTotal += filme.getPreco();
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setFilmes(filmes);
        locacao.setDataLocacao(new Date());
        locacao.setDataDevolucao(DataUtils.adicionarDias(new Date(), 1));
        locacao.setValor(precoTotal);

        //persistir

        return locacao;
    }

   }
