package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cores jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean cheque;
	private static boolean chequeMate;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cores.BRANCO;
		comecoDoJogo();
	}

	public int getTurno() {
		return turno;
	}

	public Cores getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheque() {
		return cheque;
	}

	public static boolean getchequeMate() {
		return chequeMate;
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

	public boolean[][] movimentosPossiveis(XadrezPosicao sourcePosicao) {
		Posicao posicao = sourcePosicao.toPosicao();
		validarSourcePosicao(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaDeXadrez fazerMovimentoXadrez(XadrezPosicao sourcePosicao, XadrezPosicao targetPosicao) {
		Posicao source = sourcePosicao.toPosicao();
		Posicao target = targetPosicao.toPosicao();
		validarSourcePosicao(source);
		validarTargetPosicao(source, target);
		Peca pecaCapturada = fazerMovimento(source, target);

		if (testeCheque(jogadorAtual)) {
			desfazerMovimento(source, target, pecaCapturada);
			throw new XadrezException("Você não pode se colocar em cheque");
		}

		cheque = (testeCheque(oponente(jogadorAtual))) ? true : false;

		if (testeChequeMate(oponente(jogadorAtual))) {
			chequeMate = true;
		} else {
			proximoTurno();
		}
		return (PecaDeXadrez) pecaCapturada;

	}

	private Peca fazerMovimento(Posicao source, Posicao target) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(source);
		p.aumentarContadorDeMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(target);
		tabuleiro.botarPeca(p, target);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao source, Posicao target, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(target);
		p.diminuirContadorDeMovimento();
		tabuleiro.botarPeca(p, source);

		if (pecaCapturada != null) {
			tabuleiro.botarPeca(pecaCapturada, target);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}

	private void validarSourcePosicao(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida não é sua.");
		}
		if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) {
			throw new XadrezException("Não tem movimentos possiveis para a peça escolhida");
		}
	}

	private void validarTargetPosicao(Posicao source, Posicao target) {
		if (!tabuleiro.peca(source).movimentoPossivel(target)) {
			throw new XadrezException("A peça escolhida não pode se mover ao destino escolhido");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}

	private Cores oponente(Cores cor) {
		return (cor == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
	}

	private PecaDeXadrez rei(Cores cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("Não exite rei da cor: " + cor + " no tabuleiro");
	}

	private boolean testeCheque(Cores cor) {
		Posicao posicaoRei = rei(cor).getXadrezPosicao().toPosicao();
		List<Peca> pecasAdversarias = pecasNoTabuleiro.stream()
				.filter(x -> ((PecaDeXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasAdversarias) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}

		return false;
	}

	private boolean testeChequeMate(Cores cor) {
		if (!testeCheque(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao source = ((PecaDeXadrez) p).getXadrezPosicao().toPosicao();
						Posicao target = new Posicao(i, j);
						Peca pecaCapturada = fazerMovimento(source, target);
						boolean testeCheque = testeCheque(cor);
						desfazerMovimento(source, target, pecaCapturada);
						if (!testeCheque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void botarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.botarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);

	}

	private void comecoDoJogo() {
		botarNovaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('b', 1, new Cavalo(tabuleiro, Cores.BRANCO));
		botarNovaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
		botarNovaPeca('d', 1, new Rainha(tabuleiro, Cores.BRANCO));
		botarNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO));
		botarNovaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
		botarNovaPeca('g', 1, new Cavalo(tabuleiro, Cores.BRANCO));
		botarNovaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
		botarNovaPeca('a', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('b', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('c', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('d', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('e', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('f', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('g', 2, new Peao(tabuleiro, Cores.BRANCO));
		botarNovaPeca('h', 2, new Peao(tabuleiro, Cores.BRANCO));

		botarNovaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('b', 8, new Cavalo(tabuleiro, Cores.PRETO));
		botarNovaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
		botarNovaPeca('d', 8, new Rainha(tabuleiro, Cores.PRETO));
		botarNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO));
		botarNovaPeca('g', 8, new Cavalo(tabuleiro, Cores.PRETO));
		botarNovaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
		botarNovaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
		botarNovaPeca('a', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('b', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('c', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('d', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('e', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('f', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('g', 7, new Peao(tabuleiro, Cores.PRETO));
		botarNovaPeca('h', 7, new Peao(tabuleiro, Cores.PRETO));

	}

}
