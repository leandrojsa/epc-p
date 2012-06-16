import java.util.ArrayList;

import javax.swing.JOptionPane;


class ElementGeneric{
  String description;
  int NumOccurrences;
  Object ponteiro;
  
  public void incrementNumOccurrences(){
	  this.NumOccurrences = this.NumOccurrences + 1;
  }

}
class ValuesInEtiqueta{
  static String nome1 = "";
  static String nome2 = "";
  static String prep = "";
  static String adjetivo = "";
  public  void clearValues(){
	nome1 = "";
	nome2 = "";
	prep = "";
	adjetivo = "";
  }
}
 
class Group{
  public ArrayList<ElementGeneric> list1Nome, list2NomeAdj,
  list3NomePrepNome, list4NomeAdjPreoNome, list5NomePrepNomeAdj, list6NomeAdjAdj;

  
  public Group(){
    list1Nome = new ArrayList<ElementGeneric>();
    list2NomeAdj = new ArrayList<ElementGeneric>();
    list3NomePrepNome = new ArrayList<ElementGeneric>();
    list4NomeAdjPreoNome = new ArrayList<ElementGeneric>();
    list5NomePrepNomeAdj = new ArrayList<ElementGeneric>();
    list6NomeAdjAdj = new ArrayList<ElementGeneric>();
  }
  
  public String ImprimeListas(){
    int i;
    String dado = ""; 
    //Lista1
    for (i=0; i<list1Nome.size(); i++){
      if (i==0){
        dado = "Lista 1 - Nome \n";        
      }
      dado = dado + list1Nome.get(i).description + "\t count: " + list1Nome.get(i).NumOccurrences + "\n"; 
    }
    //Lista2
    for (i=0; i<list2NomeAdj.size(); i++){
        if (i==0){
          dado = dado +"Lista 2 - Nome e Adjetivo \n";        
        }
        dado = dado + list2NomeAdj.get(i).description + "\t count: " + list2NomeAdj.get(i).NumOccurrences +"\n"; 
      }
    //Lista3
    for (i=0; i<list3NomePrepNome.size(); i++){
        if (i==0){
          dado = dado + "Lista 3 - Nome, Preposição Adjetivo\n";        
        }
        dado = dado + list3NomePrepNome.get(i).description + "\t count: " + list3NomePrepNome.get(i).NumOccurrences +"\n"; 
      }
    //Lista4
    for (i=0; i<list4NomeAdjPreoNome.size(); i++){
        if (i==0){
          dado = dado + "Lista 3 - Nome, Adjetivo, Preposição e Nomr\n";        
        }
        dado = dado + list4NomeAdjPreoNome.get(i).description + "\t count: " + list4NomeAdjPreoNome.get(i).NumOccurrences +"\n"; 
      }
    //Lista5
    for (i=0; i<list5NomePrepNomeAdj.size(); i++){
        if (i==0){
          dado = dado + "Lista 5 - Nome, Preposição, Nome e Adjetivo\n";        
        }
        dado = dado + list5NomePrepNomeAdj.get(i).description + "\t count: " + list5NomePrepNomeAdj.get(i).NumOccurrences +"\n"; 
      }
    //Lista6
    for (i=0; i<list6NomeAdjAdj.size(); i++){
        if (i==0){
          dado = dado + "Lista 5 - Nome, Adjetivo e Adjetivo\n";        
        }
        dado = dado + list6NomeAdjAdj.get(i).description + "\t count: " + list6NomeAdjAdj.get(i).NumOccurrences +"\n"; 
      }
    return dado;
  }
  
  
public void addElementInList( ArrayList<ElementGeneric> lista, String descricao){
	  int indiceElement = -1;
	  indiceElement  =locateElementInList(lista, descricao);
	  if (!descricao.isEmpty()){
		  if (indiceElement ==-1) {
				ElementGeneric elementList= new ElementGeneric();
				elementList.description=descricao;
				elementList.NumOccurrences = 1;
				lista.add(elementList);
				JOptionPane.showMessageDialog(null, "palavra " + descricao +" inserida na lista");
			}
			else {
			  lista.get(indiceElement).incrementNumOccurrences();	
				
			}	
  
	  }
	  
  }
	  
  public static int locateElementInList(ArrayList<ElementGeneric> lista, String descricao){
    int returnvalue = -1;
	  for (int i = 0; i < lista.size(); i++) {
		  if (lista.get(i).description.equalsIgnoreCase(descricao)){
			  returnvalue = i;
			  break;			  
		  }
	  }
	  return returnvalue;
	  
  }
    
} 

public class Auxiliar {
	public static int getPosOcurence(String data, String subString, int pos, int numOcurrence, boolean retunPosWithSubStr){
	    int posSubString = -1;
	    int countOcurrence = 0;
	    posSubString =  data.indexOf(subString, pos);
	    do{
	      if (numOcurrence > data.length()) {
	    	  break;
	      }
	        
	  	  if (posSubString!=-1){
	  	    countOcurrence = countOcurrence + 1;  	
	  	  }
	  	  if (countOcurrence >= numOcurrence){
	  		break;
	  	  }
	  	  posSubString =  data.indexOf(subString, posSubString+ 1);
	  	  
	    }while(posSubString!=-1);
	    if (retunPosWithSubStr && (posSubString>-1)) 
	      return posSubString + subString.length();	
	    	
	    else
	      return posSubString;
	}
	
  public static int getPreviosOcurence(String data, String subString, int posPesquisa, int numOcurrence){
	//int posSubString = -1;
	int countOcurrence = 0;
	int posSubStr = posPesquisa;
	String strAux = "";
	int countvoltas = 0;
		
	if ((posPesquisa != -1) && (!data.isEmpty()) && (!subString.isEmpty())) {
		if  (((posSubStr - subString.length())!=-1) && ((posSubStr - subString.length())< posSubStr))
		    strAux = Auxiliar.RecortString(data, posSubStr - subString.length(),posSubStr);
		    do
		    { 		      
		      if (strAux.equalsIgnoreCase(subString)){
		  	    posSubStr = posSubStr - subString.length();  
		  	    countOcurrence = countOcurrence +1;
		  	    if (countOcurrence == numOcurrence){
			      break;	  
			    }	
		  	  }	 
		  	  posSubStr = posSubStr -1;	  
		  	  if (((posSubStr - subString.length())!=-1) && ((posSubStr - subString.length())< posSubStr))
		  	    strAux = Auxiliar.RecortString( data, posSubStr - subString.length(),posSubStr);
		    }while((posSubStr >-1));	
		
	}
	return posSubStr;
 }
 
  public static String includeSpaceTheBegin(String text){
	    String returnValue = text;
	    if (!text.substring(0, 1).equalsIgnoreCase(" ")){
	      returnValue = " " + text;	
	    }
	    return returnValue;
	  }
  
  public static String extractEtiquetaByDelimitadores(String sequence, String subStrInicial, 
			 int posBuscaSubStrInicial, int countOcurrenceSubInicial, 
			 String subStrFinal, int posBuscaSubStrFinal, int countOcurrenceSubfinal){
		 String etiqueta = "";
		 int inicioSeq = getPosOcurence(sequence, subStrInicial,posBuscaSubStrInicial ,countOcurrenceSubInicial, false);
	     int fimSeq =getPosOcurence(sequence, subStrFinal,posBuscaSubStrFinal ,countOcurrenceSubfinal,false);
	     
	     if( inicioSeq < fimSeq)
	       etiqueta = sequence.substring(inicioSeq + subStrInicial.length() , fimSeq);
	    
	     return etiqueta;
	 }

  
  public static String RecortString (String textoStr, int posInicial, int posFinal){
	  String returnValue = textoStr;
	  if (!textoStr.isEmpty()){
		  if ((posInicial > -1) &&(posFinal > -1) && (posInicial <posFinal) && (textoStr.length() > 1) &&
			(posFinal <= textoStr.length())){
			returnValue = textoStr.substring(posInicial, posFinal);	  
		  }
	  }
	  return returnValue;
	  
  }
}
