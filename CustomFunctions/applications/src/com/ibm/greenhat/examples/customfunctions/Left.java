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

public class Left extends Function {
	private Function m_fInput = null;
	private Function m_fLength = null;

	public Left() {
	}

	protected Left(Function f1, Function f2) {
		m_fInput = f1;
		m_fLength = f2;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new Left((Function) params.get(0), (Function) params.get(1));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("Left");
		String input = m_fInput.evaluateAsString(data);
		String lengthStr = m_fLength.evaluateAsString(data);
		int length = Integer.parseInt(lengthStr);
		if (length < 0)
			length = 0;
		if (length > input.length())
			length = input.length();
		String output = input.substring(0, length);
		return "\"" + output + "\"";
	}
}
