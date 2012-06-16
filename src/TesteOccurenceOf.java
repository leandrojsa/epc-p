import java.io.ObjectInputStream.GetField;

import javax.swing.JOptionPane;


public class TesteOccurenceOf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String data, substr, aux;
       int numocurrence;
       int posinicial, posEncontrado;
	   data = JOptionPane.showInputDialog(null, "Insira uma frase");
	   substr = JOptionPane.showInputDialog(null, "Insira o trecho que deseja procurar");
	   aux = JOptionPane.showInputDialog(null, "Insira a posição a partir da qual deseja procurar");
	   posinicial = Integer.parseInt(aux);
	   aux = JOptionPane.showInputDialog(null, "Insira a seguencia de ocorrencias que deseja procurar");
	   numocurrence = Integer.parseInt(aux);
	   //posEncontrado = getPreviosOcurence(data, substr,posinicial,numocurrence);
	   //JOptionPane.showMessageDialog(null, posEncontrado);
	   //JOptionPane.showMessageDialog(null, posEncontrado + "treco = " + 
		//	   data.subSequence(posEncontrado ,  posEncontrado + substr.length() ));
	}
} 	
	


