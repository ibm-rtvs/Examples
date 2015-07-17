package com.ibm.greenhat.examples.customfunctions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class PrintBinConsole extends Function  {
		/**
		 * NumberOfArguments - determines which call is executed.
		 */
		//private int NumberOfArguments;
		
		private Function m_fInput = null;
		//private Function m_fLength = null;

		public PrintBinConsole(){
		}
		protected PrintBinConsole(Function f1) {
			m_fInput = f1;
		}
		
		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
			return new PrintBinConsole((Function) params.get(0));
		}

		/**
		 * <P>On-line: <B>"Provide the formatted integer (as a string) based on provided string"</B>
		 * <P>Returns from the string the x number of characters.
		 * When int is negative, it's reset to 0
		 * When int is longer than the string it gets the string length.
		 * 
		 * <span class="strong">Example:</span>
		 * 
		 * <PRE>
	     * COBOL_parseInteger("00012")
		 * </PRE>
		 * <P>will return "12"
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

			String datastring = m_fInput.evaluateAsString(data);
			String hexoutput = "";
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String text = sdfDate.format(now);
			System.out.println(text);
			
			System.out.println("Converted string: "+datastring);
			
			//Charset characterSet = Charset.forName("US-ASCII");
			//byte[] b = datastring.getBytes(characterSet);
			
		    System.out.println(field("Char","L")+field("Hex","L")+field("ASCII","R"));
			for ( int i = 0; i < datastring.length(); ++i ) {
				   char c = datastring.charAt( i );
				   int j = (int) c;
				   String Hex=Integer.toHexString(j);
				   hexoutput=hexoutput+Hex;
				   System.out.println(field(""+c,"L")+field(Hex.toUpperCase(),"L")+field(String.valueOf(j),"R"));
				   }
			hexoutput=hexoutput.toUpperCase();
			return "\"" + hexoutput + "\"";		
			
		}
		String field(String input, String alignment) {
			String output=input;
			while (output.length()<5) {
				if (alignment.equals("R")) {
					output=" "+output;
				} else {
					output=output+" ";
				}
			}
			return output;
		}
	}



