@SuppressWarnings("unchecked")
public class Comparador implements java.util.Comparator {
	public int compare(Object o1, Object o2) {  
	    ElementGeneric c1 = (ElementGeneric) o1;  
	    ElementGeneric c2 = (ElementGeneric) o2;  
	    return c1.description.compareTo(c2.description);  
	}
}