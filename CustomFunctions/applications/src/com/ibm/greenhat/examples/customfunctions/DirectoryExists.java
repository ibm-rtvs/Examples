package com.ibm.greenhat.examples.customfunctions;

import java.io.File;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class DirectoryExists extends Function  {

		private Function m_fFile = null;


		public DirectoryExists(){
		}
		protected DirectoryExists(Function f1) {
			m_fFile = f1;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
					return new DirectoryExists((Function) params.get(0));
		}
		
		/**
		 * <P>On-line: <B>"Checks if a directory exists."</B>
		 * <P>Good to know:
		 * 
		 * <span class="strong">Example:</span>
		 * 
		 * <PRE>
		 * diristhere=directoryExists("C:\tmp");
		 * </PRE>
		 * <P>will return boolean true
		 * 
		 * @param dirstr  the directory (type: String)
		 * 
		 * @return <code>value</code> - existence (type Boolean)
		 * 
		 * 
		 */
		@Override	
		public Boolean evaluate(Object data) {
			Boolean output = true;
			MESSAGE.printASIS();
			System.out.println("directoryExists");
			String filePathString = m_fFile.evaluateAsString(data);
			System.out.println("Check: "+filePathString);

			File f = new File(filePathString);
			if(f.exists() && f.isDirectory()) {
				output = true;
			} else {
				output = false;
			}
			System.out.println("Result: "+ output);
			return output;		
		}
	}



