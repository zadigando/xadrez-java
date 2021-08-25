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

	private void comecoDoJogo() {
		tabuleiro.botarPeca(new Torre(tabuleiro, Cores.BRANCO), new Posicao(2, 1));
		tabuleiro.botarPeca(new Rei(tabuleiro, Cores.PRETO), new Posicao(0, 4));
		tabuleiro.botarPeca(new Rei(tabuleiro, Cores.BRANCO), new Posicao(7, 4));

	}
	

}
