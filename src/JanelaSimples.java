import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaSimples extends JFrame {
	JTextPane texto = new JTextPane();
	JMenuItem procLista = new JMenuItem("Listas de Palavras Chave");
	JMenuItem procPalavraChave = new JMenuItem("Extração de Palavras-Chave");
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
		JMenuItem procPalavraChave = new JMenuItem("Extração de Palavras-Chave");
		JMenuItem listarGrupo1 = new JMenuItem("Grupo1");
		JMenuItem listarGrupo2 = new JMenuItem("Grupo2");
		
		
		menuArquivo.add(arqImport);
		menuArquivo.add(arqSair);
		menuProcessar.add(procLista);
		menuProcessar.add(procPalavraChave);
		menuListar.add(listarGrupo1);
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
					EPC.Process();
					
				}
					
			}
		});
		
		listarGrupo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (texto.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Insira um texto etiquetado");
				}
				else{
					if (EPC.EnableListarGroup1()){
					  EPC.ImprimeListInGroup1();	
					}
					
				}
					
			}
		});
       
	}
	
 
	
	public static void main(String[] args) {
		new JanelaSimples();
	}
}
