package xadrez;

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
	
	private void botarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.botarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
	}
	
	private void comecoDoJogo() {
		botarNovaPeca('b', 6, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO));
		botarNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO));

	}
	

}
