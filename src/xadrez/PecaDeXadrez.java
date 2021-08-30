package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {

	private Cores cor;
	private int contadorDeMovimento;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cores getCor() {
		return cor;
	}
	
	public int getContadorDeMovimento() {
		return contadorDeMovimento; 
	}
	
	public void aumentarContadorDeMovimento() {
		contadorDeMovimento++;
	}
	
	public void diminuirContadorDeMovimento() {
		contadorDeMovimento--;
	}
	
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.fromPosicao(posicao);
	 
	}
	
	protected boolean temUmaPecaAdversaria(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
