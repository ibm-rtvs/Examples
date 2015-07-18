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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

public class COBOL_parseNumber extends Function {

	private Function m_fInput = null;

	public COBOL_parseNumber() {
	}

	protected COBOL_parseNumber(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new COBOL_parseNumber((Function) params.get(0));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("COBOL_parseNumber");
		String output = null;
		boolean negative = false;
		String datastring = m_fInput.evaluateAsString(data);

		// Assume sign can be left side or right side or no sign....
		if (datastring.charAt(0) == '+'
				|| datastring.charAt(datastring.length() - 1) == '+') {
			datastring = datastring.replaceAll("\\+", "");
			negative = false;
		}
		if (datastring.charAt(0) == '-'
				|| datastring.charAt(datastring.length() - 1) == '-') {
			datastring = datastring.replaceAll("-", "");
			negative = true;
		}

		// Remove any leading zero's
		while (datastring.charAt(0) == '0') {
			datastring = datastring.substring(1);
		}

		// Just replace any , notation with a .
		datastring = datastring.replace(",", ".");
		if (datastring.indexOf('.') != -1) {
			NumberFormat format = NumberFormat.getInstance(Locale.US);
			Number number = null;
			try {
				number = format.parse(datastring);
			} catch (ParseException e) {
				MESSAGE.error("COBOL_parseNumber gave an error");
				MESSAGE.error("Input string" + datastring);
				e.printStackTrace();
			}
			double inputf = number.doubleValue();
			if (negative)
				inputf = -1 * inputf;
			output = Double.toString(inputf);
		} else {
			int inputi = Integer.parseInt(datastring);
			if (negative)
				inputi = -1 * inputi;
			output = Integer.toString(inputi);
		}
		return "\"" + output + "\"";
	}
}
