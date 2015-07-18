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

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

	public class COBOL_SignLeadingSeparate extends Function  {
		private Function m_fInput = null;
		private Function m_fLength = null;
		private int NumberOfArguments;

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
		
		@Override
		public Function create(int size, Vector params) {
			if (size==1) return new COBOL_SignLeadingSeparate((Function) params.get(0));
			
			return new COBOL_SignLeadingSeparate((Function) params.get(0),(Function) params.get(1));
		}

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



