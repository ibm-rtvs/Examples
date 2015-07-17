package com.ibm.greenhat.examples.customfunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
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
	// TODO : SuppressWarnings should be removed.
	@Override
	public Function create(int size, Vector params) {
		if (size==1) return  new GetCentralProperty((Function) params.get(0));
		return new GetCentralProperty((Function) params.get(0),(Function) params.get(1));
	}
	/**
	 * <P>On-line: <B>"Trim both sides of string and give length"</B>
	 *
	 * <span class="strong">Example:</span>
	 * 
	 * <PRE>intLenghtOfTimmedString = trimlength(" Dog barks            ");</PRE>
	 * <P>will return 9.
	 * 
	 * @param input  the input string, the string to be trimmed and measured (type: String)
	 * 
	 * @return <code>length</code> - the length of the trimmed string (type int)
	 * 
	 * 
	 */
	
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
