@SuppressWarnings("unchecked")
public class Comparador2 implements java.util.Comparator {
	public int compare(Object o1, Object o2) {  
	    ElementGeneric c1 = (ElementGeneric) o1;  
	    ElementGeneric c2 = (ElementGeneric) o2;  
	    return c1.NumOccurrences - c2.NumOccurrences;
	}
}