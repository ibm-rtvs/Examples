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
import java.util.Date;
import java.util.Calendar;

public class MESSAGE {
	static void printASIS() {
		System.out.println("Custom Function provided on AS-IS basis.");
		System.out.println("https://github.com/ibm-rtvs/Examples/tree/master/CustomFunctions");
	}
	static void printASIS(String module) {
		System.out.println("Custom Function provided on AS-IS basis.");
		System.out.println("https://github.com/ibm-rtvs/Examples/tree/master/CustomFunctions");
		System.out.println("Custom Function: "+module);
	}
	static void log(String msg) {
		printtime();
		System.out.println(" INFO: "+msg);
	}
	static void error(String msg) {
		printtime();
		System.out.println(" ERROR: "+msg);
	}
	static void printtime() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
		String text = sdfDate.format(now);
		System.out.print(text);
	}
}
