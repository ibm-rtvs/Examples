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
	public class PrintConsole extends Function  {
		/**
		 * NumberOfArguments - determines which call is executed.
		 */
		//private int NumberOfArguments;
		
		private Function m_fInput = null;
		//private Function m_fLength = null;

		public PrintConsole(){
		}
		protected PrintConsole(Function f1) {
			m_fInput = f1;
		}
		
		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
			return new PrintConsole((Function) params.get(0));
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
			Calendar calendar = Calendar.getInstance();
			 
			// 2) get a java.util.Date from the calendar instance.
//			    this date will represent the current instant, or "now".
			Date now = calendar.getTime();
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String text = sdfDate.format(now);
			System.out.print(text);
			System.out.println(" "+datastring);
			return "\"\"";		
			
		}
	}



