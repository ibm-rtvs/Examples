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
	public class Sleep extends Function  {
		private Function m_fInput = null;
		//private Function m_fLength = null;

		public Sleep(){
		}
		protected Sleep(Function f1) {
			m_fInput = f1;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
					return new Sleep((Function) params.get(0));
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
			Long length = Long.parseLong(input);
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String text = sdfDate.format(now);
			System.out.print(text);
			System.out.println(" Sleeping for "+input+"ms");

			try {
				Thread.sleep(length);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "\"\"";		
		}
	}



