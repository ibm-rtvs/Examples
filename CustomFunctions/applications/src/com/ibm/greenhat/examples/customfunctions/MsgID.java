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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

public class MsgID extends Function {

	private int NumberOfArguments;

	private Function m_fLength = null;

	public MsgID() {
		NumberOfArguments = 0;
	}

	protected MsgID(Function f1) {
		m_fLength = f1;
		NumberOfArguments = 1;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		if (size == 1)
			return new MsgID((Function) params.get(0));
		return new MsgID();
	}

	/**
	 * Convert integer to string with padding of 0 if int is one position.
	 * 
	 * @param i
	 *            the inter to convert to string
	 * @return istr the string with the integer, possibly padded with a 0.
	 */
	private String format2Number(int i) {
		String istr = Integer.toString(i);
		if (i < 10)
			istr = "0" + istr;
		return istr;
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("MsgID");
		String output = null;
		int length = 0;
		if (NumberOfArguments == 1) {
			length = Integer.parseInt(m_fLength.evaluateAsString(data));
		} else {
			// Default
			length = 20;
		}
		// Some robustness on length
		if (length < 0)
			length = 0;

		Calendar d = new GregorianCalendar();
		int yyyy = d.get(Calendar.YEAR);
		int MM = d.get(Calendar.MONTH);
		int dd = d.get(Calendar.DAY_OF_MONTH);
		int hh = d.get(Calendar.HOUR_OF_DAY);
		int mm = d.get(Calendar.MINUTE);
		int ss = d.get(Calendar.SECOND);

		String yyyystr = Integer.toString(yyyy);
		String MMstr = format2Number(MM);
		String ddstr = format2Number(dd);
		String hhstr = format2Number(hh);
		String mmstr = format2Number(mm);
		String ssstr = format2Number(ss);

		output = yyyystr + MMstr + ddstr + hhstr + mmstr + ssstr;

		if (length < output.length()) {
			output = output.substring(0, length);
		}

		while (output.length() < length) {
			String padChar = Integer.toString((int) (Math.random() * 10));
			output = output + padChar;
		}

		return "\"" + output + "\"";
	}
}
