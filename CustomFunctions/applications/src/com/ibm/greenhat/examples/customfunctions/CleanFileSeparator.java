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

public class CleanFileSeparator extends Function {
	private Function m_fInput = null;

	public CleanFileSeparator() {
	}

	protected CleanFileSeparator(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new CleanFileSeparator((Function) params.get(0));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("CleanFileSeparator");

		String output = "";
		String input = m_fInput.evaluateAsString(data);
		input = input.trim();
		output = cleanFileSeparator(input);

		MESSAGE.log("input: " + input);
		MESSAGE.log("ouput: " + output);

		return "\"" + output + "\"";
	}

	static String cleanFileSeparator(String input) {
		String output;
		input = clean(input, '\\');
		input = clean(input, '/');
		output = replaceFileSeparator(input, System.getProperty("file.separator"));
		return output;
	}

	static String replaceFileSeparator(String input, String fstring) {
		String output = "";
		char c;
		for (int i = 0; i < input.length(); ++i) {
			c = input.charAt(i);
			if (c == '\\' || c == '/') {
				output = output + fstring;
			} else {
				output = output + c;
			}
		}
		return output;
	}

	static String clean(String input, char repl) {
		String output = "";
		char c;
		char cprev;
		cprev = '`';
		for (int i = 0; i < input.length(); ++i) {
			c = input.charAt(i);
			if (c == cprev && c == repl) {
				// Current c is equal to previous c, so skip it.
			} else {
				output = output + c;
				cprev = c;
			}
		}
		return output;
	}
}
