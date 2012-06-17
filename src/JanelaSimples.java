import javax.swing.*;

import ptstemmer.exceptions.PTStemmerException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaSimples extends JFrame {
	JEditorPane texto = new JEditorPane();
	JMenuItem procLista = new JMenuItem("Listas de Palavras Chave");
	JMenuItem procPalavraChave = new JMenuItem("Extra��o de Palavras-Chave");
	AlgoritmoEPC EPC;
	
	public JanelaSimples() {
		this.setTitle("Algoritmo EPC-P");
		this.setSize(640,480);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(texto);
		
		JMenuBar barra = new JMenuBar();
		//Menus
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenu menuProcessar = new JMenu("Processar");
		JMenu menuListar = new JMenu("Listar");
		//Submenu
		JMenuItem arqSair = new JMenuItem("Sair");
		JMenuItem arqImport = new JMenuItem("Importar");
		JMenuItem procLista = new JMenuItem("Listas de Palavras Chave");
		JMenuItem procPalavraChave = new JMenuItem("Extra��o de Palavras-Chave");
		JMenuItem listarGrupo1 = new JMenuItem("Grupo1");
		JMenuItem listarGrupo2 = new JMenuItem("Grupo2");
		JMenuItem listarLista1 = new JMenuItem("Lista1");
				
		menuArquivo.add(arqImport);
		menuArquivo.add(arqSair);
		menuProcessar.add(procLista);
		menuProcessar.add(procPalavraChave);
		menuListar.add(listarGrupo1);
		menuListar.add(listarGrupo2);
		menuListar.add(listarLista1);
		barra.add(menuArquivo);
		barra.add(menuProcessar);
		barra.add(menuListar);
		this.setJMenuBar(barra);
		
		//eventos

		arqSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
 
		procLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (texto.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Insira um texto etiquetado");
				}
				else{
					EPC = null;
					EPC = new AlgoritmoEPC(texto.getText());
					try {
						EPC.Process();
					} catch (PTStemmerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
					
			}
		});
		
		listarGrupo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isValidText()){				
					if (EPC.EnableListarGroup1()){
					  EPC.ImprimeListInGroup1();	
					}					
				}					
			}
		});
		
		listarGrupo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isValidText()){
				  if (EPC.EnableListarGroup1()){
					EPC.ImprimeListInGroup2();	
				  }
					
				}
					
			}
		});
		
		listarLista1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isValidText()){
					EPC.ImprimeLista1();	
				}	
			}
		});
		
		
       
	}
	
 
	public boolean isValidText(){
	  boolean returnResult = true;	
	  if (texto.getText().isEmpty()){
		JOptionPane.showMessageDialog(null, "Insira um texto etiquetado");
		returnResult = false;
	  }
	  return(returnResult);
	   
	}
	
	public static void main(String[] args) {
		//new JanelaSimples();
	}
}
