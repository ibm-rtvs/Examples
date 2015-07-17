package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class COBOL_SignLeadingSeparate extends Function  {
		/**
		 * NumberOfArguments - determines which call is executed.
		 */
		private int NumberOfArguments;
		private Function m_fInput = null;
		private Function m_fLength = null;

		public COBOL_SignLeadingSeparate(){
		}
		protected COBOL_SignLeadingSeparate(Function f1) {
			m_fInput = f1;
			NumberOfArguments = 1;
		}
		protected COBOL_SignLeadingSeparate(Function f1,Function f2) {
			m_fInput = f1;
			m_fLength = f2;
			NumberOfArguments = 2;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
			if (size==1) return new COBOL_SignLeadingSeparate((Function) params.get(0));
			
			return new COBOL_SignLeadingSeparate((Function) params.get(0),(Function) params.get(1));
		}

		/**
		 * <P>On-line: <B>"Format the integer, padded with 0, sign in front"</B>
		 * Returns from the string the x number of characters.
		 * When int is negative, it's reset to 0
		 * When int is longer than the string it gets the string length.
		 * 
		 * <span class="strong">Example:</span>
		 * 
		 * <PRE> tags[number]=-18
		 * intLenghtOfTimmedString = COBOL_SignLeadingSeparate(tags[number]);</PRE>
		 * <P>will return "-18"
		 *
		 * <PRE>tags[number]=-18
		 *  intLenghtOfTimmedString = COBOL_SignLeadingSeparate(tags[number],5);</PRE>
		 * <P>will return "-00018"
		 * 
		 * @param tag[number]  the reference to a tag containing the interget to format (type: tag-reference)
		 * @param length  the number of positions for the integer (type: integer)
		 * 
		 * @return <code>value</code> - the formatted integer (type String)
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
			
			// Set Sign
			String sign = "";
			if (input<0) {
				sign="-";
				input=-1*input;
			} else {
				sign="+";
			}
			
			// Pad with 0
			output = Integer.toString(input);
			//if (length>output.length()) length=output.length();
			while (output.length()<length) {
				output="0"+output;
			}
			
			// Add the sign
			output = sign + output;

			return "\"" + output + "\"";		
		}
	}



