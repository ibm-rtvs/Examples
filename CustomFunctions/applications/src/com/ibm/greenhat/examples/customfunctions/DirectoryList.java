/**
 * Copyright 2014 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Marc van Lint
 */
package com.ibm.greenhat.examples.customfunctions;


import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

	public class DirectoryList extends Function  {

		private Function m_fFile = null;


		public DirectoryList(){
		}
		protected DirectoryList(Function f1) {
			m_fFile = f1;
		}

		@SuppressWarnings("rawtypes")
		
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
			MESSAGE.printASIS("DirectoryList");
			String filePathString = m_fFile.evaluateAsString(data);

			
			File folder = new File(filePathString);
			File[] listOfFiles = folder.listFiles();

			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			        MESSAGE.log("File " + listOfFiles[i].getName());
					output.add(listOfFiles[i].getName());
			      } else if (listOfFiles[i].isDirectory()) {
			        // MESSAGE.log("Directory " + listOfFiles[i].getName());
			      }
			    }
			return output;		
		}
	}



