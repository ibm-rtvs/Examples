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

public class MidString extends Function {
	private Function m_fInput = null;
	private Function m_fLeft = null;
	private Function m_fRight = null;

	private int NumberOfArguments;
	
	
	public MidString() {
	}

	protected MidString(Function f1, Function f2) {
		NumberOfArguments = 2;

		m_fInput = f1;
		m_fLeft = f2;
		MESSAGE.log("2 Aurguments");
	}
	protected MidString(Function f1, Function f2, Function f3) {
		NumberOfArguments = 3;

		m_fInput = f1;
		m_fLeft = f2;
		m_fRight = f3;
		MESSAGE.log("3 Aurguments");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		if (size == 2)
			return new MidString((Function) params.get(0), (Function) params.get(1));
		return new MidString((Function) params.get(0), (Function) params.get(1), (Function) params.get(2));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("MidString");
		String output="";
		String input = m_fInput.evaluateAsString(data);
		String leftStr = m_fLeft.evaluateAsString(data);
		if (NumberOfArguments==2) {
			int p1= input.indexOf(leftStr)+leftStr.length();
			//MESSAGE.log("P1:"+p1);
			output = input.substring(p1);
		} else {
			String rightStr = m_fRight.evaluateAsString(data);
			int p1= input.indexOf(leftStr)+leftStr.length();
			int p2 = input.indexOf(rightStr,p1+1);
			//MESSAGE.log("P1:"+p1);
			//MESSAGE.log("P2:"+p2);
			output = input.substring(p1,p2);
		}
		return "\"" + output + "\"";
	}
}
