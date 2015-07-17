package com.ibm.greenhat.examples.customfunctions;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class COBOL_parseNumber extends Function  {
		/**
		 * NumberOfArguments - determines which call is executed.
		 */
		//private int NumberOfArguments;
		
		private Function m_fInput = null;
		//private Function m_fLength = null;

		public COBOL_parseNumber(){
		}
		protected COBOL_parseNumber(Function f1) {
			m_fInput = f1;
		}
		
		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
			return new COBOL_parseNumber((Function) params.get(0));
		}

		/**
		 * <P>On-line: <B>"Return the value of a COBOL formatted number"</B>
		 * <P>Returns a number (based on INT or DOUBLE) from the string.
		 * <BR>Dot and comma are allowed as sparator
		 * 
		 * Acceptable strings:
		 *     -000012
		 *     000012-
		 *     +0000012.34
		 *     0000012,34-
		 * 
		 * @param String - Input string
		 * 
		 * @return String - Formatted String 
		 * 
		 * 
		 */
		@Override	
		// TODO SIGN NEGATIVE and POSITIVE NEEDS TO BE PARSED.
		public Object evaluate(Object data) {
			MESSAGE.printASIS();
			String output = null;
			String datastring = m_fInput.evaluateAsString(data);
			boolean negative = false;
			//boolean floatnumber = false;
			// Assume sign can be left side or right side or no sign....
			if (datastring.charAt(0)=='+' || datastring.charAt(datastring.length()-1)=='+') {
				datastring=datastring.replaceAll("+", "");
			}
			if (datastring.charAt(0)=='-' || datastring.charAt(datastring.length()-1)=='-') {
				datastring=datastring.replaceAll("-", "");
				negative = true;
			}
			while (datastring.charAt(0)=='0') {
				datastring=datastring.substring(1);
			}
			// Just replace any , notation with a .
			datastring=datastring.replaceAll(",", "\\.");
			//System.out.println("*******"+datastring);
			if (datastring.indexOf('.')!=-1) {
				//System.out.println("******* DOUBLE");
				//floatnumber=true;
				  NumberFormat format = NumberFormat.getInstance(Locale.US);
				    Number number=null;
					try {
						number = format.parse(datastring);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    double inputf = number.doubleValue();
				if (negative) inputf=-1*inputf;
				output = Double.toString(inputf);
			} else {
				//System.out.println("******* INT");
				int inputi = Integer.parseInt( datastring );
				if (negative) inputi=-1*inputi;
				output = Integer.toString(inputi);
			}
			return "\"" + output + "\"";		
		}
	}



