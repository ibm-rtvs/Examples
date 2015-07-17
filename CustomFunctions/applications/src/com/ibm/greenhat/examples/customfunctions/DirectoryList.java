package com.ibm.greenhat.examples.customfunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class DirectoryList extends Function  {

		private Function m_fFile = null;


		public DirectoryList(){
		}
		protected DirectoryList(Function f1) {
			m_fFile = f1;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
					return new DirectoryList((Function) params.get(0));
		}
		
		/**
		 * <P>On-line: <B>"Provide a list of files in the pointed directory"</B>

		 * <span class="strong">Example:</span>
		 * 
		 * <PRE>
	     * tags["timeMS"]=1423980298302983L
		 * tags["timeMSstr"]=""+tags["timeMS"];
		 * directoryList("C:\RIT-Projects");
		 * </PRE>
		 * <P>will return a list of files in this directory.
		 * 
		 * @param dirstr  the directory (type: String)
		 * 
		 * @return <code>value</code> - the list (type ArrayList)
		 * 
		 * 
		 */
		@Override	
		public ArrayList<String> evaluate(Object data) {
			ArrayList<String> output = new ArrayList<String>();
			MESSAGE.printASIS();
			System.out.println("directoryList");
			String filePathString = m_fFile.evaluateAsString(data);

			
			File folder = new File(filePathString);
			File[] listOfFiles = folder.listFiles();

			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			        System.out.println("File " + listOfFiles[i].getName());
					output.add(listOfFiles[i].getName());
			      } else if (listOfFiles[i].isDirectory()) {
			        System.out.println("Directory " + listOfFiles[i].getName());
			      }
			    }
			return output;		
		}
	}



