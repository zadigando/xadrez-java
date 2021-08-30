package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaDeXadrez;
import xadrez.XadrezException;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();

		while (true) {
			try {
				UI.limparTela();
				UI.printPartida(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Ponto de partida: ");
				XadrezPosicao source = UI.lerXadrezPosicao(sc);
				
				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(source);
				UI.limparTela();
				UI.printTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				
				System.out.println();
				System.out.print("Destino: ");
				XadrezPosicao target = UI.lerXadrezPosicao(sc);
	
				PecaDeXadrez pecaCapturada = partidaXadrez.fazerMovimentoXadrez(source, target);
				
				if (pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
			}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}	

	}

}
