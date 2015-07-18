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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

public class Log extends Function {
	private Function m_fFile = null;
	private Function m_fInput = null;
	private int NumberOfArguments;

	public Log() {
	}

	protected Log(Function f1) {
		m_fInput = f1;
		NumberOfArguments = 1;
	}

	protected Log(Function f1, Function f2) {
		m_fInput = f1;
		m_fFile = f2;
		NumberOfArguments = 2;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		if (size == 1)
			return new Log((Function) params.get(0));
		return new Log((Function) params.get(0), (Function) params.get(1));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("Log");

		// Print out to console
		String input = m_fInput.evaluateAsString(data);
		input = input.trim();
		// Output to Console
		MESSAGE.log(input);
		// Output to RIT GUI
		

		// If file is given, add to file.
		if (NumberOfArguments == 2) {
			String file = m_fFile.evaluateAsString(data);
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				out.println(input);
				out.close();
			} catch (IOException e) {
				MESSAGE.error("Log gave an error on output file "+file);
			}
		}
		return "\"\"";
	}
}
