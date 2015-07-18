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

public class SingleSlash extends Function {
	private Function m_fInput = null;

	public SingleSlash() {
	}

	protected SingleSlash(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new SingleSlash((Function) params.get(0));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("SingleSlash");

		String output = "";
		String input = m_fInput.evaluateAsString(data);
		int length = input.length();
		input = input.trim();
		char c1;
		char c2;
		for (int i = 0; i < length - 1; ++i) {
			// Check if current character and following characters are /
			c1 = input.charAt(i);
			c2 = input.charAt(i + 1);

			if (c1 == '/' && c2 == '/') {
				// Both are /
				output = output + "/";
				// Skip the next /
				i = i + 1;
			} else {
				// Normal character, just add it
				output = output + c1;
				if (i == length - 2) {
					// Add last char, which can be a single /
					output = output + c2;
				}

			}
		}

		return "\"" + output + "\"";
	}
}
