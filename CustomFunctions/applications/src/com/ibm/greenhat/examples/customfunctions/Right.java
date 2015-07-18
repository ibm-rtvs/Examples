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

public class Right extends Function {

	private Function m_fInput = null;
	private Function m_fLength = null;

	public Right() {
	}

	protected Right(Function f1, Function f2) {
		m_fInput = f1;
		m_fLength = f2;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new Right((Function) params.get(0), (Function) params.get(1));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("Right");

		String input = m_fInput.evaluateAsString(data);
		int inputLength = input.length(); // Length of inputstring
		String lengthRightStr = m_fLength.evaluateAsString(data);
		// We convert to integer so we can compare
		int lengthRight = Integer.parseInt(lengthRightStr);

		if (lengthRight < 0)
			lengthRight = 0;
		if (lengthRight > inputLength)
			lengthRight = inputLength;

		String output = input.substring(inputLength - lengthRight);

		return "\"" + output + "\"";
	}
}
