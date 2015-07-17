package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
		public class Right extends Function  {
			/**
			 * @param String - Input string
			 * @param Int - Number of characters
			 * @return String - The left most Int chars of String
			 * @param ${param} the ${bare_field_name} to set
			 * Returns from the string the x number of characters.
			 * When int is negative, it's reset to 0
			 * When int is longer than the string it gets the string length.
			 * 
			 */
			/**
			 * This function will return the left string
			 */
			private Function m_fInput = null;
			/**
			 * This function will return the left string
			 */
			private Function m_fLength = null;


			public Right(){
			}
			protected Right(Function f1,Function f2) {
				m_fInput = f1;
				m_fLength = f2;
			}

			@SuppressWarnings("rawtypes")
			// TODO : SuppressWarnings should be removed.
			@Override
			public Function create(int size, Vector params) {
						return new Right((Function) params.get(0),(Function) params.get(1));
			}
			/**
			 * <P>On-line: <B>"Provide the right most x chars of string"</B>
			 *
			 * <P>When int is negative, it's reset to 0
			 * When int is longer than the string it gets the string length.
			 * 
			 * <span class="strong">Example:</span>
			 * 
			 * <PRE>left("Rational Test Workbench",5);</PRE>
			 * <P>will return "bench"
			 * 
			 * @param String - Input string
			 * @param Int - Number of characters
			 * 
			 * @return <code>value</code> - the formatted ID (type String)
			 * 
			 * 
			 */
			@Override	
			public Object evaluate(Object data) {
				MESSAGE.printASIS();
				
				String input = m_fInput.evaluateAsString(data);
				int inputLength = input.length();  // Length of inputstring
				String lengthRightStr = m_fLength.evaluateAsString(data);
				int lengthRight = Integer.parseInt(lengthRightStr); // Number of characters from the right
				if (lengthRight<0) lengthRight=0;
				if (lengthRight>inputLength) lengthRight=inputLength;
				//String output = input.substring(inputLength-lengthRight,inputLength);
				String output = input.substring(inputLength-lengthRight);
				//System.out.println(output);
				return "\"" + output + "\"";		
			}
		}




