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
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
	public class FileSize extends Function  {

		private Function m_fFile = null;

		public FileSize(){
		}
		protected FileSize(Function f1) {
			m_fFile = f1;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Function create(int size, Vector params) {
					return new FileSize((Function) params.get(0));
		}
		
		@Override	
		public Object evaluate(Object data) {
			MESSAGE.printASIS("FileExists");
			long output = 0;
			String outputString = "";
			String filePathString = m_fFile.evaluateAsString(data);
			System.out.println("Check: "+filePathString);

			File f = new File(filePathString);
			output=f.length();
			outputString = Long.toString(output);
			System.out.println("Result: "+ outputString);
			//return "\"" + outputString + "\"";		
			return output;		
		}
	}



