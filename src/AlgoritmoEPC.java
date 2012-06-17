import java.util.*;

import javax.swing.JOptionPane;

import ptstemmer.Stemmer;
import ptstemmer.exceptions.PTStemmerException;

  public class AlgoritmoEPC {
  public String texto, textoAux ;
  public  Group grupo1, grupo2Radicais;
  final String EtiqAdjetivo = "/ADJ";
  final String EtiqNome = "/CN";
  final String EtiqPrep = "/PREP";
  final String EtiqDemarcador = "#fs ";
  final String EtiqSeparadorSentenca = "/";
  final String EtiqSeparadorEspaco = " ";
 
  public AlgoritmoEPC(String TextoOriginal) {
 	texto = TextoOriginal;
 	grupo1 = new Group();
	grupo2Radicais = new Group();
	
  }
  
  public boolean EnableListarGroup1(){
	return (grupo1.list1Nome.size()>0)
	  || (grupo1.list2NomeAdj.size()>0)
	  || (grupo1.list3NomePrepNome.size()>0)
	  || (grupo1.list5NomeAdjPreoNome.size()>0)
	  || (grupo1.list6NomePrepNomeAdj.size()>0)
	  || (grupo1.list4NomeAdjAdj.size()>0); 	  
  }
  
  public void ImprimeListInGroup1(){
	String dado;
	dado =  grupo1.ImprimeListas();
	JOptionPane.showMessageDialog(null, dado);
  }
  
  public void ImprimeListInGroup2(){
	String dado;
	dado =  grupo2Radicais.ImprimeListas();
	JOptionPane.showMessageDialog(null, "Radicais das Palavras: " + dado);
  }
	
  public boolean Process() throws PTStemmerException{
	boolean valueReturn;
	valueReturn = extractListInGrupo1()&& extractListInGrupo2();
	
	  
	
	/*JOptionPane.showMessageDialog(null, 
			  grupo1.list6NomePrepNomeAdj.get(0).description + " " +grupo1.list6NomePrepNomeAdj.get(0).textWithEtiqueta +
			  " \n" + grupo1.list5NomeAdjPreoNome.get(0).description + " " +grupo1.list5NomeAdjPreoNome.get(0).textWithEtiqueta+
			  " \n" + grupo1.list4NomeAdjAdj.get(0).description + " " +grupo1.list4NomeAdjAdj.get(0).textWithEtiqueta);
	*/
	return valueReturn;  
  }

  public boolean extractListInGrupo1(){
	extractNome(); 
	extractNomeAndAdjetivo();
	extractNomePrepNome();
	extractNomeAjdAdj();
	extractNomeAdjPrepNome();
	extractNomePrepNomeAdj();
	
	return(true);
	  
  }
  
  public boolean extractListInGrupo2() throws PTStemmerException{
	String palavraProcessada;
    //Lista1
	for(int i=0; i< grupo1.list1Nome.size(); i++){
      palavraProcessada = extractRadicaisInPalavra(grupo1.list1Nome.get(i).description);      	
      if (!palavraProcessada.isEmpty()){
    	int indice = grupo2Radicais.addElementInList(grupo2Radicais.list1Nome, palavraProcessada, 
    	  grupo1.list1Nome.get(i),grupo1.list1Nome.get(i).textWithEtiqueta);
    	if(indice != -1){ 
    		grupo2Radicais.list1Nome.get(indice).NumOccurrences = countOcurrenceOfRadicaisInTextOriginal(palavraProcessada);
    		System.out.println(palavraProcessada + ": " + grupo2Radicais.list1Nome.get(indice).NumOccurrences);
    	}
    	
      }
    }
    //Lista2
    for(int i=0; i< grupo1.list2NomeAdj.size(); i++){
      palavraProcessada = extractRadicaisInText(grupo1.list2NomeAdj.get(i).description);
        if (!palavraProcessada.isEmpty()){
        	int indice =  grupo2Radicais.addElementInList(grupo2Radicais.list2NomeAdj, palavraProcessada, 
      	    grupo1.list2NomeAdj.get(i), grupo1.list2NomeAdj.get(i).textWithEtiqueta);
        	if(indice != -1){ 
        		grupo2Radicais.list2NomeAdj.get(indice).NumOccurrences = countOcurrenceOfRadicaisInTextOriginal(palavraProcessada);
        		//System.out.println(palavraProcessada + ": " + grupo2Radicais.list1Nome.get(indice).NumOccurrences);
        	}
      }
    }
    //Lista3
    for(int i=0; i< grupo1.list3NomePrepNome.size(); i++){
      palavraProcessada = extractRadicaisInText(grupo1.list3NomePrepNome.get(i).description);      	
      if (!palavraProcessada.isEmpty()){
    	  int indice = grupo2Radicais.addElementInList(grupo2Radicais.list3NomePrepNome, palavraProcessada,
    	  grupo1.list3NomePrepNome.get(i),grupo1.list3NomePrepNome.get(i).textWithEtiqueta);
    	  if(indice != -1){ 
      		grupo2Radicais.list3NomePrepNome.get(indice).NumOccurrences = countOcurrenceOfRadicaisInTextOriginal(palavraProcessada);
      		//System.out.println(palavraProcessada + ": " + grupo2Radicais.list1Nome.get(indice).NumOccurrences);
      	}
      }
    }
   //Lista4
    for(int i=0; i< grupo1.list4NomeAdjAdj.size(); i++){
      palavraProcessada = extractRadicaisInText(grupo1.list4NomeAdjAdj.get(i).description);      	
      if (!palavraProcessada.isEmpty()){
    	  int indice = grupo2Radicais.addElementInList(grupo2Radicais.list4NomeAdjAdj, palavraProcessada,
    	  grupo1.list4NomeAdjAdj.get(i),grupo1.list4NomeAdjAdj.get(i).textWithEtiqueta);
    	  if(indice != -1){ 
      		grupo2Radicais.list4NomeAdjAdj.get(indice).NumOccurrences = countOcurrenceOfRadicaisInTextOriginal(palavraProcessada);
      		//System.out.println(palavraProcessada + ": " + grupo2Radicais.list1Nome.get(indice).NumOccurrences);
      	}
      }
    }
   //Lista5
    for(int i=0; i< grupo1.list5NomeAdjPreoNome.size(); i++){
      palavraProcessada = extractRadicaisInText(grupo1.list5NomeAdjPreoNome.get(i).description);      	
      if (!palavraProcessada.isEmpty()){
    	  int indice = grupo2Radicais.addElementInList(grupo2Radicais.list5NomeAdjPreoNome, palavraProcessada,
    	  grupo1.list5NomeAdjPreoNome.get(i), grupo1.list5NomeAdjPreoNome.get(i).textWithEtiqueta);
    	  if(indice != -1){ 
      		grupo2Radicais.list5NomeAdjPreoNome.get(indice).NumOccurrences = countOcurrenceOfRadicaisInTextOriginal(palavraProcessada);
      		//System.out.println(palavraProcessada + ": " + grupo2Radicais.list1Nome.get(indice).NumOccurrences);
      	}
      }
    }
  //Lista6
    for(int i=0; i< grupo1.list6NomePrepNomeAdj.size(); i++){
      palavraProcessada = extractRadicaisInText(grupo1.list6NomePrepNomeAdj.get(i).description);      	
      if (!palavraProcessada.isEmpty()){
    	  int indice = grupo2Radicais.addElementInList(grupo2Radicais.list6NomePrepNomeAdj, palavraProcessada,
    	  grupo1.list6NomePrepNomeAdj.get(i), grupo1.list6NomePrepNomeAdj.get(i).textWithEtiqueta);
    	  if(indice != -1){ 
      		grupo2Radicais.list6NomePrepNomeAdj.get(indice).NumOccurrences = countOcurrenceOfRadicaisInTextOriginal(palavraProcessada);
      		//System.out.println(palavraProcessada + ": " + grupo2Radicais.list1Nome.get(indice).NumOccurrences);
      	}
      }
    }
   	return (true);
  }
  
  
  public int countOcurrenceOfRadicaisInTextOriginal(String radical){
    textoAux = texto;
    int ocurrence = 0;
    int pos = 0;
    
    String arrayRadical[] = radical.split(" ");
    
    // caso o radical tenha mais de uma palavra
    if (arrayRadical.length > 1){
      while(pos < textoAux.length()){
    	pos = textoAux.indexOf(arrayRadical[0], pos);
    	if(pos == -1){
 	       break;
 	     }else {
 	    	if(textoAux.charAt(pos - 1) != ' ' &&
 	          	 textoAux.charAt(pos -1) != '/' && 
 	          	 textoAux.charAt(pos - 1) != '>'){
 	    		pos += arrayRadical[0].length();
 	    		continue;
 	    	}else{
	 	       pos = textoAux.indexOf(" ", pos) + 1;
	 	       int current_pos = pos;
	 	       int i = 1;
	 	       while(i < arrayRadical.length){
	 	    	   current_pos = textoAux.indexOf(arrayRadical[i], current_pos);
	 	    	   if(current_pos == pos){
	 	    		  current_pos = textoAux.indexOf(" ", current_pos) + 1;
	 	    		  pos = current_pos;
	 	    	   }else{
	 	    		   current_pos = -1;
	 	    		   break;
	 	    	   }
	 	    	   i++;
	 	       }
	 	       if(current_pos != -1){
	 	    	  ocurrence++;
	 	    	  pos = current_pos;
	 	       }
 	    	}
 	     }
      }
    }
    // caso o radical seja uma unica palavra
    else{    
      while(pos < textoAux.length()){
	    pos = textoAux.indexOf(radical, pos);
        if(pos == -1){
          break;
        }else{
          //para verificar o radical foi encontrado no meio de uma palavra
          if(textoAux.charAt(pos - 1) == ' ' ||
        	 textoAux.charAt(pos - 1) == '/' || 
        	 textoAux.charAt(pos - 1) == '>'){
        	  ocurrence++;
          }
    	  pos += radical.length();
        }
      }
    }
	
	return ocurrence;
  }
 
  public String extractRadicaisInText(String text) throws PTStemmerException
  {
	String dado = "";
	Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.PORTER);
	st.enableCaching(1000000); //Eu ainda n�o sei quanto devo colocar no cache
	String lista[] = text.split(" ");
	for (int i=0; i< lista.length; i++){
	  if (!dado.isEmpty()){
	    dado = dado + " "+ st.getWordStem(lista[i]);  
	  }
	  else { dado = st.getWordStem(lista[i]);}	  
    }  
	
	return(dado);
  }

  public String extractRadicaisInPalavra(String dado) throws PTStemmerException
  {
	Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
	st.enableCaching(1000000); //Eu ainda n�o sei quanto devo colocar no cache
	String palavra;	
	palavra = st.getWordStem(dado);
	return(palavra);
  }

  	
  private void extractNome(){
    //A abrevia��o de um nome no extrator de palavras � "/CN" 
	textoAux = texto;
	String palavraExtraida = "";
	int posnome,posPreviousEtiqueta, posCorteInicial, posCorteFinal; 
	do{
	  posnome = Auxiliar.getPosOcurence(textoAux, EtiqNome,0 ,1, false);
	  if (posnome == -1)  break;
		posPreviousEtiqueta  = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, posnome,1);
	  if (posPreviousEtiqueta == -1) break;
	  if ((posnome>-1)&&(posPreviousEtiqueta>-1)){
	    String sentenca = (String) textoAux.subSequence(posPreviousEtiqueta, posnome + EtiqNome.length());
	    palavraExtraida = Auxiliar.extractEtiquetaByDelimitadores(sentenca, EtiqSeparadorEspaco, 0, 1, 
	      EtiqSeparadorSentenca, 0, 1);
	    textoAux = textoAux.substring(textoAux.indexOf(sentenca, 0)+sentenca.length(),textoAux.length());
	    //JOptionPane.showMessageDialog(null, palavraExtraida + "\n  senten�a "+ sentenca + "\n " + textoAux);
	    if (!palavraExtraida.isEmpty()){
	 	  grupo1.addElementInList(grupo1.list1Nome, palavraExtraida, sentenca);
	    }
	  }  
	} while (!palavraExtraida.isEmpty());  
	JOptionPane.showMessageDialog(null, "Qtd. nomes: " +grupo1.list1Nome.size());  
  }
  
  private void extractNomeAndAdjetivo(){
    /*A abrevia��o de um nome juntamente com um adjetivo no extrator de palavras � respectivamente:
    "/CN" "/ADJ"
    A estrutura para estas sentencas no extrator aparece da seguinte forma:
    /nome/texto/CN#fs  Nome/text/ADJ	
    Para demarcar o texto podemos utlizar a barra "/" ou o espa�o " " para demarcarmos a sequencia 
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
        //Recorta a String que cont�m o trecho do texto procurado
        sentenca = Auxiliar.RecortString(textoAux, posInicialNome1, posAdj +  EtiqAdjetivo.length());
        if (isValidSequenceWithNomeAdj(sentenca, valuesNomeAdj)){
          //Recorta a String que do texto
    	  grupo1.addElementInList(grupo1.list2NomeAdj, valuesNomeAdj.nome1 + " " +valuesNomeAdj.adjetivo,
    			  sentenca);
    	  
        }
        textoAux =  Auxiliar.RecortString(textoAux, posAdj + EtiqAdjetivo.length(), textoAux.length());  
         /* JOptionPane.showMessageDialog(null,  "posNome1 " +posNome1 +  " Adjetivo   " + posAdj + 
		  "\n sentenca " +  sentenca + "\n  texto: "+textoAux ); */
        
      }
    }while(!sentenca.isEmpty());
    valuesNomeAdj = null;
    JOptionPane.showMessageDialog(null, "Qtd. NomeAdjetivos: " +grupo1.list2NomeAdj.size()); 
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
	    //Recorta a String que cont�m o trecho do texto procurado	    	
	    sentenca = Auxiliar.RecortString(textoAux, Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, posPrep, 2),
     	Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posPrep ,2, true)+EtiqNome.length()); 
	    if (!sentenca.isEmpty()){
	      //Recorta a String que do texto
	      textoAux =  Auxiliar.RecortString(textoAux, 
	      Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posPrep ,2, true)+ EtiqPrep.length(), textoAux.length());
	    }
	    /*JOptionPane.showMessageDialog(null,  "posPrep " +posPrep + " nome1" + posNome1 + " nome2   " + posNome2 + 
	    		  "\n sentenca " +  sentenca + "\n  "+textoAux );*/
	    if (isValidSequenceWithNomeAdjNome(sentenca, valuesNomeAdjNome)){
	      grupo1.addElementInList(grupo1.list3NomePrepNome,
	        valuesNomeAdjNome.nome1 + " " + valuesNomeAdjNome.prep + " "+ valuesNomeAdjNome.nome2, 
	        sentenca);
	    }
	    	      
	  }
	}while(!sentenca.isEmpty());
	valuesNomeAdjNome = null;
  } 
  
  private void extractNomeAdjPrepNome(){
    //Exemplo:  Ambiente telem�tico de ensino 
    int posNome1, posNome2, posAdj, posPrep;
	String sentenca = "";
	textoAux = texto;
	Boolean isValidSequence;
	ValuesInEtiqueta valuesNomeAdjPrepNome = new ValuesInEtiqueta();
	int countOcurrence = 1;
	do{
	  posNome2 = Auxiliar.getPosOcurence(textoAux, EtiqNome,0 ,2, true);
	  if (posNome2 <= 0) break;
	  posPrep = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posNome2, 1);
	  posAdj = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posNome2, 2);
	  posNome1 = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posNome2, 3);
	  sentenca = Auxiliar.RecortString(textoAux, Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, 
	    posNome1, 3),
	  posNome2+EtiqAdjetivo.length()); 
	  //JOptionPane.showMessageDialog(null, sentenca);
	  isValidSequence = isValidSequenceWithNomeAdjPrepNome(sentenca, valuesNomeAdjPrepNome);
	  if ((posPrep <=0) || (posAdj <=0)|| (posNome2<=0) ||(posNome1>posNome2)|| (!isValidSequence))
	  {
		textoAux = Auxiliar.RecortString(textoAux, posNome2 + EtiqNome.length(),textoAux.length());
		sentenca = textoAux;
		//JOptionPane.showMessageDialog(null, "Entrou no If");
	  } 
	  else
	  {
		textoAux = Auxiliar.RecortString(textoAux, posNome2+ EtiqNome.length(),textoAux.length());
		if (isValidSequence){
		  grupo1.addElementInList(grupo1.list5NomeAdjPreoNome, valuesNomeAdjPrepNome.nome1 + " " + 
			valuesNomeAdjPrepNome.adjetivo + " " +valuesNomeAdjPrepNome.prep + " "+ valuesNomeAdjPrepNome.nome2,
			sentenca);	
		}
	  }	  
	}while(!sentenca.isEmpty());
	//JOptionPane.showMessageDialog(null, "saiu do while " + sentenca +   "valor while: "+ !sentenca.isEmpty());
	valuesNomeAdjPrepNome = null;
  }
  
  private boolean isValidSequenceWithNomeAdjPrepNome(String sequence, ValuesInEtiqueta valuesEtiqueta){
    boolean valueReturn = true;	 
	String etq1, etq2, etq3, etq4 = null;
	int posEtq1,posEtq2, posEtq3, posEtq4; 
	sequence = Auxiliar.includeSpaceTheBegin(sequence);
	//JOptionPane.showMessageDialog(null,sequence);
	posEtq1 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 2,false);
	posEtq2 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 4,false);
	posEtq3 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 5,false);
	posEtq4 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 7,false);
	        		 
	etq1 = Auxiliar.RecortString(sequence,posEtq1, posEtq1 + EtiqNome.length());
	etq2 = Auxiliar.RecortString(sequence,posEtq2, posEtq2 + EtiqAdjetivo.length());
	etq3 = Auxiliar.RecortString(sequence,posEtq3, posEtq3 + EtiqPrep.length());
	etq4 = Auxiliar.RecortString(sequence,posEtq4, posEtq4 + EtiqNome.length());
	
	valueReturn = (etq1.equalsIgnoreCase(EtiqNome)&& etq2.equalsIgnoreCase(EtiqAdjetivo)&&
	  etq3.equalsIgnoreCase(EtiqPrep)&& etq4.equalsIgnoreCase(EtiqNome));
	/*JOptionPane.showMessageDialog(null, "Verifica��o da etiqueta" + valueReturn +
	  "et1: " + etq1 +
	  " et2: " + etq2 + 
	  " et3: " + etq3 + 
	  " et4: " + etq4);*/
	if (valueReturn){
	  valuesEtiqueta.clearValues();
	  valuesEtiqueta.nome1 = Auxiliar.RecortString(sequence, 0, Auxiliar.getPosOcurence(sequence,
	    EtiqSeparadorSentenca, 0, 1,false));
	  valuesEtiqueta.adjetivo = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    1, EtiqSeparadorSentenca, 0,3);
	  valuesEtiqueta.prep = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    2, EtiqSeparadorSentenca, 0,5);
	  valuesEtiqueta.nome2 = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    3, EtiqSeparadorSentenca, 0,6);
	}
	    
	/*JOptionPane.showMessageDialog(null, "nome: " + etq1 + "valor "+ valuesEtiqueta.nome1 +
	  " adj1: " +etq2 + "valor: "+ valuesEtiqueta.adjetivo+
	  "\n Prep:" +etq3 + "valor: "+ valuesEtiqueta.prep + "\n" +
	  "\n Nome2:" +etq4 + "valor: "+ valuesEtiqueta.nome2 + "\n"+
	  valueReturn + sequence);*/
	return valueReturn;
  }
  
  private void extractNomePrepNomeAdj(){
	//Exemplo:  Desenvolvimento de atividades cooperativas
	int posNome1, posNome2, posAdj, posPrep;
	String sentenca = "";
	textoAux = texto;
	Boolean isValidSequence;
	ValuesInEtiqueta valuesNomeAdjPrepNome = new ValuesInEtiqueta();
	int countOcurrence = 1;
	do{
	  posNome2 = Auxiliar.getPosOcurence(textoAux, EtiqNome,0 ,2, true);
	  if (posNome2 <= 0) break;
	  posPrep = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posNome2, 1);
	  posNome1 = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posNome2, 2);
	  posAdj = Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posNome2 ,2, true);
	  sentenca = Auxiliar.RecortString(textoAux, Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, 
	    posNome1, 3),
	  posAdj+EtiqAdjetivo.length()); 
	  /*JOptionPane.showMessageDialog(null, sentenca);*/
	  isValidSequence = isValidSequenceWithNomePrepNomeAdj(sentenca, valuesNomeAdjPrepNome);
	  if ((posPrep <=0) || (posAdj <=0)|| (posNome2<=0) ||(posNome1>posNome2)|| (!isValidSequence))
	  {
		textoAux = Auxiliar.RecortString(textoAux, 
		  Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, 
			posNome1, 1),
			textoAux.length());
		sentenca = textoAux;
		//JOptionPane.showMessageDialog(null, "Entrou no If");
	  } 
	  else
	  {
		textoAux = Auxiliar.RecortString(textoAux, posNome2+ EtiqNome.length(),textoAux.length());
		if (isValidSequence){
		  grupo1.addElementInList(grupo1.list6NomePrepNomeAdj, valuesNomeAdjPrepNome.nome1 + " " + 
			valuesNomeAdjPrepNome.prep + " " +valuesNomeAdjPrepNome.nome2 + " "+ valuesNomeAdjPrepNome.adjetivo,
			sentenca);	
		}
	  }	  
	}while(!sentenca.isEmpty());
	//JOptionPane.showMessageDialog(null, "saiu do while " + sentenca +   "valor while: "+ !sentenca.isEmpty());
	valuesNomeAdjPrepNome = null;
  }  

  private boolean isValidSequenceWithNomePrepNomeAdj(String sequence, ValuesInEtiqueta valuesEtiqueta){
	boolean valueReturn = true;	 
	String etq1, etq2, etq3, etq4 = null;
	int posEtq1,posEtq2, posEtq3, posEtq4; 
	sequence = Auxiliar.includeSpaceTheBegin(sequence);
	//JOptionPane.showMessageDialog(null,sequence);
	posEtq1 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 2,false);
	posEtq2 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 3,false);
	posEtq3 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 5,false);
	posEtq4 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 7,false);
		        		 
	etq1 = Auxiliar.RecortString(sequence,posEtq1, posEtq1 + EtiqNome.length());
	etq2 = Auxiliar.RecortString(sequence,posEtq2, posEtq2 + EtiqPrep.length());
	etq3 = Auxiliar.RecortString(sequence,posEtq3, posEtq3 + EtiqNome.length());
	etq4 = Auxiliar.RecortString(sequence,posEtq4, posEtq4 + EtiqAdjetivo.length());
	
	valueReturn = (etq1.equalsIgnoreCase(EtiqNome)&& etq2.equalsIgnoreCase(EtiqPrep)&&
	  etq3.equalsIgnoreCase(EtiqNome)&& etq4.equalsIgnoreCase(EtiqAdjetivo));
	/*JOptionPane.showMessageDialog(null, "Verifica��o da etiqueta" + valueReturn +
	  "et1: " + etq1 +
	  " et2: " + etq2 + 
	  " et3: " + etq3 + 
	  " et4: " + etq4);*/
	if (valueReturn){
	  valuesEtiqueta.clearValues();
	  valuesEtiqueta.nome1 = Auxiliar.RecortString(sequence, 0, Auxiliar.getPosOcurence(sequence,
			    EtiqSeparadorSentenca, 0, 1,false));
	  valuesEtiqueta.prep = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    1, EtiqSeparadorSentenca, 0,3);
	  valuesEtiqueta.nome2 = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    2, EtiqSeparadorSentenca, 0,4);
	  valuesEtiqueta.adjetivo = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    3, EtiqSeparadorSentenca, 0,6);
	}
	/*JOptionPane.showMessageDialog(null, "nome: " + etq1 + " valor = "+ valuesEtiqueta.nome1 +
	  " Prep: " +etq2 + " valor= "+ valuesEtiqueta.prep+
	  "\n nome2:" +etq3 + "valor=  "+ valuesEtiqueta.nome2 + "\n" +
	  "\n adjetivo:" +etq4 + "valor= "+ valuesEtiqueta.adjetivo + "\n"+
	  valueReturn + sequence);*/
	return valueReturn;
  }
  
  private void extractNomeAjdAdj(){
	//Exemplo:  programa��o/PROGRAMA��O/CN#fs linear/LINEAR/ADJ#gs inteira/INTEIRO/ADJ#fs 
	int posNome, posAdj1, posAdj2;
	String sentenca = "";
	textoAux = texto;
	Boolean isValidSequence;
	ValuesInEtiqueta valuesNomeAdjAdj = new ValuesInEtiqueta();
	int countOcurrence = 1;
	do{
	  posAdj1 = Auxiliar.getPosOcurence(textoAux, EtiqAdjetivo,0 ,countOcurrence, true);
	  if (posAdj1 <= 0) break;
	  posNome = Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorSentenca, posAdj1, 3);
	  posAdj2 = Auxiliar.getPosOcurence(textoAux, EtiqSeparadorSentenca,posAdj1 ,2, true);
	  if (posAdj2 <= 0) break;
	  
	  sentenca = Auxiliar.RecortString(textoAux, Auxiliar.getPreviosOcurence(textoAux, EtiqSeparadorEspaco, posNome, 1),
			  posAdj2+EtiqAdjetivo.length()); 
	  
	  isValidSequence = isValidSequenceWithNomeAdjAdj(sentenca, valuesNomeAdjAdj);
	  if ((posNome ==-1) || ((posNome <=0)||(posAdj2<posAdj1))|| !isValidSequence)
	  {
		textoAux = Auxiliar.RecortString(textoAux, posAdj1 + EtiqAdjetivo.length(),textoAux.length());
		sentenca = textoAux;
		
	  } 
	  else
	  {
		textoAux = Auxiliar.RecortString(textoAux, posAdj2+ EtiqAdjetivo.length(),textoAux.length());
		if (isValidSequence){
		  grupo1.addElementInList(grupo1.list4NomeAdjAdj, 
		    valuesNomeAdjAdj.nome1 + " " + valuesNomeAdjAdj.adjetivo + " "+ valuesNomeAdjAdj.adjetivo2,
		    sentenca);	
		}
		
	  }	  
		    
	}while(!sentenca.isEmpty());
	
	//JOptionPane.showMessageDialog(null, "saiu do while " + sentenca +   "valor while: "+ !sentenca.isEmpty());
	valuesNomeAdjAdj = null;
  } 
  
  
  private boolean isValidSequenceWithNomeAdjAdj(String sequence, ValuesInEtiqueta valuesEtiqueta){
    boolean valueReturn = true;	 
	String etq1, etq2, etq3 = null;
	int posEtq1,posEtq2, posEtq3; 
	sequence = Auxiliar.includeSpaceTheBegin(sequence);
	//JOptionPane.showMessageDialog(null,sequence);
	posEtq1 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 2,false);
	posEtq3 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 6,false);
	posEtq2 =Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 4,false);
	        		
	etq1 = Auxiliar.RecortString(sequence,posEtq1, posEtq1 + EtiqNome.length());
	etq2 = Auxiliar.RecortString(sequence,posEtq2, posEtq2 + EtiqAdjetivo.length());
	etq3 = Auxiliar.RecortString(sequence,posEtq3, posEtq3 + EtiqAdjetivo.length());
	
	valueReturn = (etq1.equalsIgnoreCase(EtiqNome)&& etq2.equalsIgnoreCase(EtiqAdjetivo)&&
	  etq3.equalsIgnoreCase(EtiqAdjetivo));
	if (valueReturn){
	  valuesEtiqueta.clearValues();
	  valuesEtiqueta.nome1 = Auxiliar.RecortString(sequence, 0, Auxiliar.getPosOcurence(sequence, EtiqSeparadorSentenca, 0, 1,false));
	  valuesEtiqueta.adjetivo = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    1, EtiqSeparadorSentenca, 0,3);
	    valuesEtiqueta.adjetivo2 = Auxiliar.extractEtiquetaByDelimitadores(sequence, EtiqSeparadorEspaco, 2,
	    2, EtiqSeparadorSentenca, 0,5);
	}
	/*JOptionPane.showMessageDialog(null, "nome: " + etq1 + "valor "+ valuesEtiqueta.nome1 +
	  " adj1: " +etq2 + "valor: "+ valuesEtiqueta.adjetivo+
	  "\nadj2:" +etq3 + "valor: "+ valuesEtiqueta.adjetivo2 + "\n" + valueReturn + sequence);*/
	return valueReturn;
	
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
	  "\n senten�a: " +sequence + "\n " + valueReturn);*/
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


} //fim da classe





 