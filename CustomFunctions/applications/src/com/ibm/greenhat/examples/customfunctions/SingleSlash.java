package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
public class SingleSlash extends Function {
	private Function m_fInput = null;

	public SingleSlash(){
	}
	
	protected SingleSlash(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	// TODO : SuppressWarnings should be removed.
	@Override
	public Function create(int size, Vector params) {
		return new SingleSlash((Function) params.get(0));
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
		
		String output = "";
		String input = m_fInput.evaluateAsString(data);
		int length = input.length();
		input=input.trim();
		char c1;
		char c2;
		for ( int i = 0; i < length-1; ++i ) {
			   c1 = input.charAt( i );
			   c2 = input.charAt( i+1 );
			   //System.out.println(" "+i +"="+ c1 + c2);
			   if (c1=='/' && c2=='/') {
				   output=output+"/";
				   i=i+1;
			   } else {
				   output=output+c1;
				   if (i==length-2){
					   // Add last char, which can not be /
					   output=output+c2;
				   }

			   }
			   //System.out.println(" "+output);
		}

		return "\"" + output + "\"";		
	}
}
