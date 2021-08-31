package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {

	public Peao(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);
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
			if (getTabuleiro().posicaoExiste(p) && getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& getTabuleiro().temUmaPeca(p2) && getContadorDeMovimento() == 0) {
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

		}
		else {
			p.setarValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setarValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& getTabuleiro().temUmaPeca(p2) && getContadorDeMovimento() == 0) {
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
		}
		return mat;	
	}
	
	
	@Override
	public String toString() {
		return "P";
	}

}
