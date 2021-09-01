package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {

	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cores cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cores.BRANCO) {
			p.setarValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().temUmaPeca(p2) && getContadorDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// #movimentoespecial en passant branca
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && temUmaPecaAdversaria(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVunerabilidade()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && temUmaPecaAdversaria(direita)
						&& getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVunerabilidade()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}

		} else {
			p.setarValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().temUmaPeca(p2) && getContadorDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// #movimentoespecial en passant branca
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && temUmaPecaAdversaria(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassantVunerabilidade()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && temUmaPecaAdversaria(direita)
						&& getTabuleiro().peca(direita) == partidaXadrez.getEnPassantVunerabilidade()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return mat;
	}
 
	@Override
	public String toString() {
		return "P";
	}

}
