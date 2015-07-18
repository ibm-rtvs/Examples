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
	public class FileExists extends Function  {

		private Function m_fFile = null;

		public FileExists(){
		}
		protected FileExists(Function f1) {
			m_fFile = f1;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Function create(int size, Vector params) {
					return new FileExists((Function) params.get(0));
		}
		
		@Override	
		public Object evaluate(Object data) {
			MESSAGE.printASIS("FileExists");

			Boolean output = false;
			String filePathString = m_fFile.evaluateAsString(data);
			MESSAGE.log("Check: "+filePathString);

			File f = new File(filePathString);
			if(f.exists() && !f.isDirectory()) {
				output = true;
			} else {
				output = false;
			}
			MESSAGE.log("Returns: "+ output);
			return output ;		
		}
	}



