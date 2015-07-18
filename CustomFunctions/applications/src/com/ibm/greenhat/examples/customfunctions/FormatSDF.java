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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

public class FormatSDF extends Function {

	private Function m_fTime = null;
	private Function m_fFormat = null;

	public FormatSDF() {
	}

	protected FormatSDF(Function f1, Function f2) {
		m_fTime = f1;
		m_fFormat = f2;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new FormatSDF((Function) params.get(0), (Function) params.get(1));
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("formatMs2Date");
		// long millis=1418408883924L - about now;
		long millis = 0;
		String timeInMs = m_fTime.evaluateAsString(data);
		String formatStr = m_fFormat.evaluateAsString(data);
		// Time in milliseconds
		millis = Long.parseLong(timeInMs);
		// String sformat="yyyy-MM-dd hh:mm:ss";
		// System.out.println("Time in ms: "+timeInMs);
		// System.out.println("Time in ms: "+millis);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		SimpleDateFormat format1 = new SimpleDateFormat(formatStr);
		String output = format1.format(cal.getTime());

		return "\"" + output + "\"";
	}
}
