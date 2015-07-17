package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class COBOL_Integer extends Function  {
		/**
		 * NumberOfArguments - determines which call is executed.
		 */
		private int NumberOfArguments;
		
		private Function m_fInput = null;
		private Function m_fLength = null;

		public COBOL_Integer(){
		}
		protected COBOL_Integer(Function f1) {
			m_fInput = f1;
			NumberOfArguments = 1;
		}
		protected COBOL_Integer(Function f1,Function f2) {
			m_fInput = f1;
			m_fLength = f2;
			NumberOfArguments = 2;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
			if (size==1) return new COBOL_Integer((Function) params.get(0));
			
			return new COBOL_Integer((Function) params.get(0),(Function) params.get(1));
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
	     * COBOL_Integer("6",4)
		 * </PRE>
		 * <P>will return "0006"
		 * 
		 * @param String - Input string
		 * @param Int - Number of characters
		 * 
		 * @return String - Formatted String
		 * 
		 * 
		 */
		@Override	
		public Object evaluate(Object data) {
			MESSAGE.printASIS();
			String output = null;
			int length = 0;
			int input = Integer.parseInt( m_fInput.evaluateAsString(data) );
			if (NumberOfArguments==2) {
				length = Integer.parseInt( m_fLength.evaluateAsString(data) );
			}
			// Some robustness on length
			if (length<0) length=0;
			
		
			// Pad with 0
			output = Integer.toString(input);
			//if (length>output.length()) length=output.length();
			while (output.length()<length) {
				output="0"+output;
			}
			
			return "\"" + output + "\"";		
		}
	}



