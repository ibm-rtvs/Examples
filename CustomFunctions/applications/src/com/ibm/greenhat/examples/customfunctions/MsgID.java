package com.ibm.greenhat.examples.customfunctions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class MsgID extends Function  {
		/**
		 * @param String - Input string
		 * @param Int - Number of characters
		 * @return String - The left most Int chars of String
		 * 
		 * Returns from the string the x number of characters.
		 * When int is negative, it's reset to 0
		 * When int is longer than the string it gets the string length.
		 * 
		 */
		private int NumberOfArguments;
		/**
		 * This function will return the length of the string
		 */
		private Function m_fLength = null;
		// TODO WORK TODO....
		public MsgID(){
			NumberOfArguments = 0;
		}
		protected MsgID(Function f1) {
			m_fLength = f1;
			NumberOfArguments = 1;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
			if (size==1) return new MsgID((Function) params.get(0));
			return new MsgID();
		}

		/**
		 * Convert integer to string with padding of 0 if int is one position.
		 * 
		 * @param i the inter to convert to string
		 * @return istr the string with the integer, possibly padded with a 0.
		 */
		private String format2Number(int i) {
			String istr = Integer.toString(i);
			if (i<10) istr="0"+ istr;
			return istr;
		}
		
		/**
		 * <P>See Method Detail for syntax.
		 * 
		 * <P>This software is provided on an "<B>AS-IS</B>" basis.
		 * This must be seen as an example.
		 * 
		 * <P>On-line: <B>"Provide a MessageID (unique string of numbers)"</B>
		 * 
		 * <P>Example:
		 * <PRE> MsgID();</PRE>
		 * <P>will return 20141211110098876062 (default length 20)
		 *
		 * <PRE> MsgID(25);</PRE>
		 * <P>will return 2014121111009887606212345 (set length 25)
		 * 
		 * @param length  the number of positions for the string (type: integer)
		 * 
		 * @return <code>value</code> - the formatted ID (type String)
		 * 
		 * 
		 */
		@Override	
		public Object evaluate(Object data) {
			MESSAGE.printASIS();
			String output = null;
			int length = 0;
			if (NumberOfArguments==1) {
				length = Integer.parseInt( m_fLength.evaluateAsString(data) );
			} else {
				// Default
				length = 20;
			}
			// Some robustness on length
			if (length<0) length=0;

			
			Calendar d = new GregorianCalendar();
			int yyyy = d.get(Calendar.YEAR);
			int MM = d.get(Calendar.MONTH);
			int dd = d.get(Calendar.DAY_OF_MONTH);
			int hh = d.get(Calendar.HOUR_OF_DAY);
			int mm = d.get(Calendar.MINUTE);
			int ss = d.get(Calendar.SECOND);

			String yyyystr = Integer.toString(yyyy);
			String MMstr = format2Number(MM);
			String ddstr = format2Number(dd);
			String hhstr = format2Number(hh);
			String mmstr = format2Number(mm);
			String ssstr = format2Number(ss);
			
			output = yyyystr + MMstr + ddstr + hhstr + mmstr + ssstr;
			

			while (output.length()<length) {
				String padChar = Integer.toString( (int)(Math.random()*10) );
				output=output + padChar;
			}
			
			return "\"" + output + "\"";		
		}
	}



