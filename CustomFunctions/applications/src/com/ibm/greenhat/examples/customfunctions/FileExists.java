package com.ibm.greenhat.examples.customfunctions;

import java.io.File;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class FileExists extends Function  {

		private Function m_fFile = null;


		public FileExists(){
		}
		protected FileExists(Function f1) {
			m_fFile = f1;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
					return new FileExists((Function) params.get(0));
		}
		
		/**
		 * <P>On-line: <B>"Provide the formatted time based on provided milliseconds"</B>
		 * <P>Good to know:
		 * <PRE>
		 * tags["value"]=1423980298302983L
		 * tags["value"].toString will give 1.42332E12
		 * tags["value"].valueOf will give the actual value
		 * ""+tags["value"].valueOf will return the actual value of in string type
		 * </PRE>
		 * 
		 * <span class="strong">Example:</span>
		 * 
		 * <PRE>
	     * tags["timeMS"]=1423980298302983L
		 * tags["timeMSstr"]=""+tags["timeMS"];
		 * formatMsDate(tags["timeMSstr"],"yyyy-MM-dd");
		 * </PRE>
		 * <P>will return "2014-12-12"
		 * 
		 * @param timeMSstr  the number of milliseconds (from 1970-01-01) (type: String)
		 * @param formatStr  the SimpleDateFormat string (type: String)
		 * 
		 * @return <code>value</code> - the formatted Date/Time (type String)
		 * 
		 * 
		 */
		@Override	
		public Boolean evaluate(Object data) {
			Boolean output = false;
			MESSAGE.printASIS();
			System.out.println("fileExists");
			String filePathString = m_fFile.evaluateAsString(data);
			System.out.println("Check: "+filePathString);

			File f = new File(filePathString);
			if(f.exists() && !f.isDirectory()) {
				output = true;
			} else {
				output = false;
			}
			System.out.println("Result: "+ output);
			return output ;		
		}
	}



