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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;

public class GetCentralProperty extends Function {
	private Function m_fInput1 = null;
	private Function m_fInput2 = null;
	private int NumberOfArguments;

	public GetCentralProperty(){
	}
	
	protected GetCentralProperty(Function f1) {
		m_fInput1 = f1;
		NumberOfArguments = 1;
	}
	
	protected GetCentralProperty(Function f1,Function f2) {
		m_fInput1 = f1;
		m_fInput2 = f2;
		NumberOfArguments = 2;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Function create(int size, Vector params) {
		if (size==1) return  new GetCentralProperty((Function) params.get(0));
		return new GetCentralProperty((Function) params.get(0),(Function) params.get(1));
	}
	
	@Override	
	public Object evaluate(Object data) {
		MESSAGE.printASIS();
		String output = null;
		String configfile="C:\\config.properties";
		String keyword = m_fInput1.evaluateAsString(data);
		keyword=keyword.trim();
		keyword=keyword.toLowerCase();
		if (NumberOfArguments==2) {
			configfile = m_fInput2.evaluateAsString(data) ;
			System.out.println("Property file used:" + configfile);
		}

	 
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream(configfile);
	 
			// load a properties file
			prop.load(input);
			output=prop.getProperty(keyword);
			// get the property value and print it out
			System.out.println("Property "+keyword+"="+output);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
	  
	return "\"" + output + "\"";		
	}
}
