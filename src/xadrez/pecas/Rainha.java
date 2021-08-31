package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Rainha extends PecaDeXadrez {

	public Rainha(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// pra cima
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// pra esquerda
		p.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// pra direita
		p.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// pra cima
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// noroeste
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setarValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nordeste
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setarValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudeste
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setarValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudoeste
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setarValores(p.getLinha() + 1, p.getColuna() - 1);
			;
		}
		if (getTabuleiro().posicaoExiste(p) && temUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	};
}
