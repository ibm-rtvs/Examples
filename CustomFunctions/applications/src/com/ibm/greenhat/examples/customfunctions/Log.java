package com.ibm.greenhat.examples.customfunctions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
public class Log extends Function {
	private Function m_fFile = null;
	private Function m_fInput = null;

	public Log(){
	}
	
	protected Log(Function f1,Function f2) {
		m_fFile = f1;
		m_fInput = f2;
	}

	@SuppressWarnings("rawtypes")
	// TODO : SuppressWarnings should be removed.
	@Override
	public Function create(int size, Vector params) {
		return new Log((Function) params.get(0),(Function) params.get(1));
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
		//int length = 0;
		String file = m_fFile.evaluateAsString(data);
		String input = m_fInput.evaluateAsString(data);
		input=input.trim();
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		    out.println(input);
		    out.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		
		return "\"\"";		
	}
}
