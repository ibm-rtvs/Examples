package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class Left extends Function  {
		private Function m_fInput = null;
		private Function m_fLength = null;

		public Left(){
		}
		protected Left(Function f1,Function f2) {
			m_fInput = f1;
			m_fLength = f2;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
					return new Left((Function) params.get(0),(Function) params.get(1));
		}
		
		/**
		 * <P>On-line: <B>"Provide the left most x chars of string"</B>
		 *
		 * <P>When int is negative, it's reset to 0
		 * When int is longer than the string it gets the string length.
		 * 
		 * <span class="strong">Example:</span>
		 * 
		 * <PRE>left("Rational Test Workbench",5);</PRE>
		 * <P>will return "Ratio"
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
			String lengthStr = m_fLength.evaluateAsString(data);
			int length = Integer.parseInt(lengthStr);
			if (length<0) length=0;
			if (length>input.length()) length=input.length();
			String output = input.substring(0,length);
			return "\"" + output + "\"";		
		}
	}



