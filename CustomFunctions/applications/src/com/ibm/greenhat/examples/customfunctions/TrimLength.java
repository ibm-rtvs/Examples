package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
public class TrimLength extends Function {
	private Function m_fInput = null;

	public TrimLength(){
	}
	
	protected TrimLength(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	// TODO : SuppressWarnings should be removed.
	@Override
	public Function create(int size, Vector params) {
		return new TrimLength((Function) params.get(0));
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
	public Integer evaluate(Object data) {
		MESSAGE.printASIS();

		Integer output = null;
		String input = m_fInput.evaluateAsString(data);
		input=input.trim();
		output=input.length();
		return output;		

	}
}
