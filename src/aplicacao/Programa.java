package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaDeXadrez;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
		UI.printTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		System.out.println("Ponto de partida: ");
		XadrezPosicao source = UI.lerXadrezPosicao(sc);
		
		System.out.println();
		System.out.println("Destino: ");
		XadrezPosicao target = UI.lerXadrezPosicao(sc);
		
		PecaDeXadrez pecaCapturada = partidaXadrez.fazerMovimentoXadrez(source, target);
		
		
		}
			
		
	}

}
