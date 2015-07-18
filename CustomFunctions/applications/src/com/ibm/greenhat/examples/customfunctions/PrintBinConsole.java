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

public class PrintBinConsole extends Function {

	private Function m_fInput = null;

	public PrintBinConsole() {
	}

	protected PrintBinConsole(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new PrintBinConsole((Function) params.get(0));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS();

		String datastring = m_fInput.evaluateAsString(data);
		String hexoutput = "";
		MESSAGE.log("Converted string: " + datastring);

		// Charset characterSet = Charset.forName("US-ASCII");
		// byte[] b = datastring.getBytes(characterSet);

		System.out.println(field("Char", "L") + field("Hex", "L") + field("ASCII", "R"));
		for (int i = 0; i < datastring.length(); ++i) {
			char c = datastring.charAt(i);
			int j = (int) c;
			String Hex = Integer.toHexString(j);
			hexoutput = hexoutput + Hex;
			System.out.println(field("" + c, "L") + field(Hex.toUpperCase(), "L") + field(String.valueOf(j), "R"));
		}
		hexoutput = hexoutput.toUpperCase();
		return "\"" + hexoutput + "\"";

	}

	String field(String input, String alignment) {
		String output = input;
		while (output.length() < 5) {
			if (alignment.equals("R")) {
				output = " " + output;
			} else {
				output = output + " ";
			}
		}
		return output;
	}
}
