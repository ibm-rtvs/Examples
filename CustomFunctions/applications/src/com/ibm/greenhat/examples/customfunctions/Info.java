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

import java.io.File;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

public class Info extends Function {

	public Info() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		return new Info();
	}

	@Override
	public Object evaluate(Object data) {
		MESSAGE.printASIS("Info");
		MESSAGE.log("Source on: https://github.com/ibm-rtvs/Examples");
		File dir1 = new File(".");
		File dir2 = new File("..");
		try {
			MESSAGE.log("Current dir : " + dir1.getCanonicalPath());
			MESSAGE.log("Parent  dir : " + dir2.getCanonicalPath());
			// http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
			MESSAGE.log("File Separator : " + System.getProperty("file.separator"));
			MESSAGE.log("Path Separator : " + System.getProperty("path.separator"));
			MESSAGE.log("Operating System Architecture: " + System.getProperty("os.arch"));
			MESSAGE.log("Operating System Name: " + System.getProperty("os.name"));
			MESSAGE.log("User home: " + System.getProperty("user.home"));
			MESSAGE.log("User name: " + System.getProperty("user.name"));
		} catch (Exception e) {
			MESSAGE.error("Info gave an error");
			e.printStackTrace();
		}
		;
		return "";
	}
}
