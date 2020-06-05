import java.util.Scanner;

public class EquationsSystemsSolver {

	public static void main(String[] args) {
		System.out.println("Sistema de equações:");
		System.out.println("|-18A +12B = -45|");
		System.out.println("| +2A -4B = +5 |");
		System.out.println("Digite o numero de variáveis: 2");
		System.out.println("Digite a matriz:");
		System.out.println("-18.0 12.0 -45.0");
		System.out.println("2.0 -4.0 5.0");
		System.out.println("V1 = 2.5 \nV2 = 0\n");
		System.out.print("Digite o numero de variáveis: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		System.out.println("\nDigite a matriz:");
		double[][] matriz = new double[n][n+1];
		for(int l=0; l < n; l++) {
			for(int c=0; c <= n; c++) {
				matriz[l][c] = sc.nextDouble();
			}
		}
		try {
			resolve(matriz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public static void resolve(double[][] matriz) throws Exception {
		int tLinha = matriz.length;
		int tColuna = matriz[0].length;
		if(tLinha+1 != tColuna) {
			System.out.println("Erro: a matrix deve possuir uma coluna a mais que o número linhas! Provalvemente esqueceste da coluna dos valores independentes");
		} else {
			int tMatrizIncompleta = tLinha;
			double[][] matrizIncompleta = new double[tLinha][tLinha];
			for(int l=0; l < tMatrizIncompleta; l++) {
				for(int c=0; c < tMatrizIncompleta; c++) {
					matrizIncompleta[l][c] = matriz[l][c];
				}
			}
			
			double[] cIndependentes = new double[tMatrizIncompleta];
			
			for(int l=0; l < tMatrizIncompleta; l++) {
				cIndependentes[l] = matriz[l][tColuna-1];
			}
			
			double[] solucao = new double[tMatrizIncompleta];
			
			double detMatrizIncompleta = determinante(matrizIncompleta);
			
			System.out.println(detMatrizIncompleta);
			
			double[] detIndependentes = new double[tMatrizIncompleta];
			
			for(int variavel=0; variavel < tMatrizIncompleta; variavel++) {
				double[][] matrizCalculo = new double[tMatrizIncompleta][tMatrizIncompleta];
				for(int c=0; c < tMatrizIncompleta; c++)  {
					if(c == variavel) {
						for(int l=0; l < tMatrizIncompleta; l++) {
							matrizCalculo[l][c] = cIndependentes[l];
						}
					} else {
						for(int l=0; l < tMatrizIncompleta; l++) {
							matrizCalculo[l][c] = matriz[l][c];
						}
					}	
				}
				detIndependentes[variavel] = determinante(matrizCalculo);
				System.out.println("\n\n" + detIndependentes[variavel]);
				
				solucao[variavel] = detIndependentes[variavel]/detMatrizIncompleta;
				
				System.out.println("V" + String.valueOf(variavel+1) + " = " + String.valueOf(solucao[variavel]));
			}
		}
	}

	public static double determinante(double[][] matriz) throws Exception {
		int tamanho = matriz.length;
		if(tamanho == 0) {
			return 0.0;
		} else if(tamanho == 1) {
			return matriz[0][0];
		} else if(matriz.length == matriz[0].length) {
			double valorDeterminante = 0;
			for(int scan = 0; scan < tamanho; scan++) {
				double[][] subMatriz = new double[(tamanho - 1)][(tamanho - 1)]; 
				double prod = matriz[0][scan];
				for(int l = 0; l < (tamanho-1); l++) {
					int desvio = 0;
					for(int c = 0; c < (tamanho-1); c++) {
						if(c == scan) {
							desvio = 1;
						}
						subMatriz[l][c] = matriz[l+1][c+desvio];
					}
				}
				double subDeterminante = determinante(subMatriz);
				prod = prod*cofator(0, scan, subDeterminante);
				valorDeterminante += prod;
			}
			return valorDeterminante;
		}
		else {
			throw new RuntimeException();
		}
	}
	
	public static double cofator(int i, int j, double determinante) {
		double resultado = ((Math.pow(-1, (i+j+2))) * determinante);
		return resultado;
	}
}
