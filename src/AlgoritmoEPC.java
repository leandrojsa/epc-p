import java.util.*;

import javax.swing.JOptionPane;


public class AlgoritmoEPC {
  public String texto, textoAux ;
  public  Group grupo1, grupo2 ;
  final String EtiqAdjetivo = "/ADJ";
  final String EtiqNome = "/CN";
  final String EtiqPrep = "/PREP";
  final String EtiqDemarcador = "#fs ";
  final String EtiqSeparadorSentenca = "/";
  final String EtiqSeparadorEspaco = " ";
 
  public AlgoritmoEPC(String TextoOriginal) {
 	texto = TextoOriginal;
 	grupo1 = new Group();
	grupo2 = new Group();
	JOptionPane.showMessageDialog(null, "Criação do grupo2");
  }
  
  public boolean EnableListarGroup1(){
	    
		 return (grupo1.list1Nome.size()>0)
		 || (grupo1.list2NomeAdj.size()>0)
		 || (grupo1.list3NomePrepNome.size()>0)
		 || (grupo1.list4NomeAdjPreoNome.size()>0)
		 || (grupo1.list5NomePrepNomeAdj.size()>0)
		 || (grupo1.list6NomeAdjAdj.size()>0); 	  
  }
  
  public void ImprimeListInGroup1(){
	  String dado;
	  dado =  grupo1.ImprimeListas();
	  JOptionPane.showMessageDialog(null, dado);
  }
	
  public boolean Process(){
	return (extractGrupoLista1());  
	  
  }

  public boolean extractGrupoLista1(){
	//extractNome(); 
	//extractNomeAndAdjetivo();
	extractNomePrepNome();
	return(true);
	  
  }

  public boolean extractGrupoLista2(){
	  return(true);	  
  }
  	
  private void extractNome(){
	//A abreviação de um nome no extrator de palavras é "/CN" 
	textoAux = texto;
	String palavraExtraida = "";
	int posnome,posPreviousEtiqueta, posCorteInicial, posCorteFinal; 
	do{
	 	posnome = Auxiliar.getPosOcurence(textoAux, EtiqNome,0 ,1, false);
		 if (posnome == -1)
			 break;
		 posPreviousEtiqueta  = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, posnome,1);
		 if (posPreviousEtiqueta == -1)
			 break;
		 if ((posnome>-1)&&(posPreviousEtiqueta>-1)){
		   String sentenca = (String) textoAux.subSequence(posPreviousEtiqueta, posnome + EtiqNome.length());
		   palavraExtraida = Auxiliar.extractEtiquetaByDelimitadores(sentenca, EtiqSeparadorEspaco, 0, 1, 
	    		   EtiqSeparadorSentenca, 0, 1);
	       textoAux = textoAux.substring(textoAux.indexOf(sentenca, 0)+sentenca.length(),textoAux.length());
	       //JOptionPane.showMessageDialog(null, palavraExtraida + "\n  sentença "+ sentenca + "\n " + textoAux);
	       if (!palavraExtraida.isEmpty()){
	 		  grupo1.addElementInList(grupo1.list1Nome, palavraExtraida);
	       }
		 }  
	}	
    while (!palavraExtraida.isEmpty());  
	 JOptionPane.showMessageDialog(null, "Qtd. nomes: " +grupo1.list1Nome.size());  
  }
  
  private void extractNomeAndAdjetivo(){
    /*A abreviação de um nome juntamente com um adjetivo no extrator de palavras é respectivamente:
    "/CN" "/ADJ"
    A estrutura para estas sentencas no extrator aparece da seguinte forma:
    /nome/texto/CN#fs  Nome/text/ADJ	
    Para demarcar o texto podemos utlizar a barra "/" ou o espaço " " para demarcarmos a sequencia 
    a ser localizada  */
    //Exemplo:  respeito/RESPEITO/CN#ms a/PREP atividades/ATIVIDADE/CN#fp
    int posInicialNome1, posNome1, posAdj;
    String sentenca = "";
    textoAux = texto;
    ValuesInEtiqueta valuesNomeAdj = new ValuesInEtiqueta();
    int countOcurrence = 1;
	
    do{
      posAdj = Auxiliar.getPosOcurence(textoAux, EtiqAdjetivo,0 ,countOcurrence, true);
      posInicialNome1 =Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, posAdj,2);
      if (posAdj == -1) break;
        posNome1 = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posAdj, 3);
      
      if ((posInicialNome1 ==-1) || ((posAdj <=0)||(posAdj<posInicialNome1))){
	    countOcurrence = countOcurrence + 1;
	    textoAux = Auxiliar.RecortString(textoAux, posNome1+1, textoAux.length());
	    sentenca = texto;
	  }
      else {
        //Recorta a String que contém o trecho do texto procurado
        sentenca = Auxiliar.RecortString(textoAux, posInicialNome1, posAdj +  EtiqAdjetivo.length());
        if (isValidSequenceWithNomeAdj(sentenca, valuesNomeAdj)){
          //Recorta a String que do texto
    	  grupo1.addElementInList(grupo1.list2NomeAdj, valuesNomeAdj.nome1 + " " +valuesNomeAdj.adjetivo);
        }
        textoAux =  Auxiliar.RecortString(textoAux, posAdj + EtiqAdjetivo.length(), textoAux.length());  
        /* JOptionPane.showMessageDialog(null,  "posNome1 " +posNome1 +  " Adjetivo   " + posAdj + 
		  "\n sentenca " +  sentenca + "\n  texto: "+textoAux ); */
        
      }
    }while(!sentenca.isEmpty());
    valuesNomeAdj = null;
  }
 
  private void extractNomePrepNome(){
//Exemplo:  respeito/RESPEITO/CN#ms a/PREP atividades/ATIVIDADE/CN#fp 
	int posPrep, posNome2, posNome1;
	String sentenca = "";
	textoAux = texto;
	ValuesInEtiqueta valuesNomeAdjNome = new ValuesInEtiqueta();
	 
	int countOcurrence = 1;
	
	do{
		
		posPrep = Auxiliar.getPosOcurence(textoAux, EtiqPrep,0 ,countOcurrence, true);
		if (posPrep == -1) break;
		posNome1 = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posPrep, 1);
		posNome2 = Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posPrep ,2, true);
		if ((posNome1 ==-1) || ((posNome2 <=0)||(posNome2<posNome1))){
	      countOcurrence = countOcurrence + 1;
	      textoAux = Auxiliar.RecortString(textoAux, 0, posPrep+1);
	      sentenca = texto;
	      
	    }
	    else
	    {   
	    	//Recorta a String que contém o trecho do texto procurado	    	
	    	sentenca = Auxiliar.RecortString(textoAux, Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, posPrep, 2),
     	      Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posPrep ,2, true)+EtiqNome.length()); 
	    	
	    	if (!sentenca.isEmpty()){
	        	//Recorta a String que do texto
	        	textoAux =  Auxiliar.RecortString(textoAux, 
	        	  Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posPrep ,2, true)+ EtiqPrep.length(), textoAux.length());
	        }
	       	JOptionPane.showMessageDialog(null,  "posPrep " +posPrep + " nome1" + posNome1 + " nome2   " + posNome2 + 
	    		  "\n sentenca " +  sentenca + "\n  "+textoAux );
	       	if (isValidSequenceWithNomeAdjNome(sentenca, valuesNomeAdjNome)){
	       		grupo1.addElementInList(grupo1.list3NomePrepNome, valuesNomeAdjNome.nome1 + " " + valuesNomeAdjNome.prep +
	       				" "+ valuesNomeAdjNome.nome2);
	       	}
	    	      
	    }
	    
	    
	}while(!sentenca.isEmpty());
	valuesNomeAdjNome = null;
  }  
  private boolean isValidSequenceWithNomeAdjNome(String sequence, ValuesInEtiqueta valuesEtiqueta){
    boolean valueReturn = true;	 
    String etq1, etq2, etq3 = null;
    int posEtq1,posEtq2, posEtq3; 
    sequence = Auxiliar.includeSpaceTheBegin(sequence);
    posEtq1 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 2,false);
    posEtq3 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 5,false);
    posEtq2 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 3,false);
        		
    etq1 = Auxiliar.RecortString(sequence,posEtq1, posEtq1 + EtiqNome.length());
    etq2 = Auxiliar.RecortString(sequence,posEtq2, posEtq2 + EtiqPrep.length());
    etq3 = Auxiliar.RecortString(sequence,posEtq3, posEtq3 + EtiqNome.length());
    
    valueReturn = (etq1.equalsIgnoreCase(EtiqNome)&& etq2.equalsIgnoreCase(EtiqPrep)&&etq3.equalsIgnoreCase(EtiqNome));
    if (valueReturn){
    	valuesEtiqueta.clearValues();
    	valuesEtiqueta.nome1 = Auxiliar.RecortString(sequence, 0, Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 1,false));
    	valuesEtiqueta.prep = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
    			1, EtiqSeparadorSentenca, 0,3);
    	valuesEtiqueta.nome2 = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
    			2, EtiqSeparadorSentenca, 0,4);
    }
    
    return valueReturn;
  }
  
  private boolean isValidSequenceWithNomeAdj(String sequence, ValuesInEtiqueta valuesEtiqueta){
	    boolean valueReturn = true;	 
	    String etq1, etq2;
	    int posEtq1,posEtq2; 
	    sequence = Auxiliar.includeSpaceTheBegin(sequence);
	    posEtq1 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 2,false);
	    posEtq2 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 4,false);
	    
	        		
	    etq1 = Auxiliar.RecortString(sequence,posEtq1, posEtq1 + EtiqNome.length());
	    etq2 = Auxiliar.RecortString(sequence,posEtq2, posEtq2 + EtiqAdjetivo.length());
	    
	    
	    valueReturn = (etq1.equalsIgnoreCase(EtiqNome)&& etq2.equalsIgnoreCase(EtiqAdjetivo));
	    if (valueReturn){
	    	valuesEtiqueta.clearValues();
	    	valuesEtiqueta.nome1 = Auxiliar.RecortString(sequence, 0, Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 1,false));
	    	valuesEtiqueta.adjetivo = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    			1, EtiqSeparadorSentenca, 0,3);
	    	
	    }
	    /*JOptionPane.showMessageDialog(null, 
	    		"PosNome:" + posEtq1 +" posAdj: " + posEtq2+
	    		"nome: "+ valuesEtiqueta.nome1 + " adj: " + valuesEtiqueta.adjetivo +
	    		"\n sentença: " +sequence + "\n " + valueReturn);*/
	    return valueReturn;
	  }
  
  private boolean hasEtiquetaAdJ(String text, int posInicial,int posFinal){
	boolean valueReturn = false;
	if (text.substring(posInicial, posFinal).equalsIgnoreCase(EtiqAdjetivo)) valueReturn = true;
	return valueReturn;
   }
   private boolean hasEtiquetaPrep(String text, int posInicial,int posFinal){
	     boolean valueReturn = false;
	     if (text.substring(posInicial, posFinal).equalsIgnoreCase(EtiqPrep)) valueReturn = true;
	     return valueReturn;
   }
 private String getpalavraAndRemoveDoTexto(String abreviacao){
	 int posFimPalavra, posInicioPalavra, posAbreviacao;
	 posInicioPalavra = 0;
	 posFimPalavra = 0;
	 posAbreviacao = textoAux.indexOf(abreviacao,0);
	 int posAux = posAbreviacao -1;
	 String palavra = "";
	 
     if ((! abreviacao.isEmpty())&&(posAbreviacao != -1) ){
    	 while (textoAux.charAt(posAux)  != '/')
		 {
		    posAux = posAux -1;
		    posFimPalavra = posAux;
		    
		 }
		 while (textoAux.charAt(posAux)  != '#')
		 {
		    posAux = posAux -1;
		    posInicioPalavra = posAux;
		 }
		  palavra =  (String) textoAux.subSequence(posInicioPalavra +4, posFimPalavra);
		  textoAux= removeSequenciaPalavraInText(posAbreviacao + abreviacao.length(), textoAux.length());
		 // JOptionPane.showMessageDialog(null, palavra + "\n  " + textoAux );
	 }
	 return palavra;
  }

private String removeSequenciaPalavraInText(int PosInicial, int PosFinal){
  return(textoAux.substring(PosInicial, PosFinal));
  
}




}





 