/**
 * PTStemmer - A Stemming toolkit for the Portuguese language (C) 2008-2010 Pedro Oliveira
 * 
 * This file is part of PTStemmer.
 * PTStemmer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PTStemmer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with PTStemmer. If not, see <http://www.gnu.org/licenses/>.
 * 
 */


import java.util.Scanner;

import javax.swing.JOptionPane;

import ptstemmer.Stemmer;
import ptstemmer.exceptions.PTStemmerException;


/**
 * System demo
 * @author Pedro Oliveira
 *
 */
public class ExemploStemmer {

	public static void main(String[] args) throws PTStemmerException {
		//ExemploStemmer ex = new ExemploStemmer();
		//ex.starter();
		
	}
	
	public void starter() throws PTStemmerException
	{
		Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
		st.enableCaching(1000);
		
		String line;
		Scanner s = new Scanner(System.in);
		System.out.println("Insert one word per line:");
		while(s.hasNext())
		{
			line = s.nextLine();
			System.out.println("Stem: "+st.getWordStem(line));
		}	
	}
}

