package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		comecoDoJogo();
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);

			}
		}
		return mat;
	}

	public PecaDeXadrez fazerMovimentoXadrez(XadrezPosicao sourcePosicao, XadrezPosicao targetPosicao) {
		Posicao source = sourcePosicao.toPosicao();
		Posicao target = targetPosicao.toPosicao();
		validarSourcePosicao(source);
		Peca pecaCapturada = fazerMovimento(source, target);
		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca fazerMovimento(Posicao source, Posicao target) {
		Peca p = tabuleiro.removerPeca(source);
		Peca pecaCapturada = tabuleiro.removerPeca(target);
		tabuleiro.botarPeca(p, target);
		return pecaCapturada;
	}

	private void validarSourcePosicao(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem");

		}
		if(!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) {
			throw new XadrezException("Não tem movimentos possiveis para a peça escolhida");
		}
	}

	private void botarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.botarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
	}

	private void comecoDoJogo() {
		botarNovaPeca('c', 1, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('c', 2, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('d', 2, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('e', 2, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('d', 1, new Rei(tabuleiro, Cores.BRANCO));

		botarNovaPeca('c', 7, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('c', 8, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('d', 7, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('e', 7, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('e', 8, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('d', 8, new Rei(tabuleiro, Cores.PRETO));

	}

}
