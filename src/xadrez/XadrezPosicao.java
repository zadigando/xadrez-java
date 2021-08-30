package xadrez;

import tabuleiro.Posicao;

public class XadrezPosicao {

	private int coluna;
	private int linha;

	public XadrezPosicao(int coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Erro ao instanciar a posição, valores são validos de a1 até h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	protected Posicao toPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}

	protected static XadrezPosicao fromPosicao(Posicao posicao) {
		return new XadrezPosicao((char) ('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}

	@Override
	public String toString() {
		return "" + coluna + linha;
	}

}
