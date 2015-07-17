package com.ibm.greenhat.examples.customfunctions;

import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
public class DoubleSlash extends Function {
	private Function m_fInput = null;

	public DoubleSlash(){
	}
	
	protected DoubleSlash(Function f1) {
		m_fInput = f1;
	}

	@SuppressWarnings("rawtypes")
	// TODO : SuppressWarnings should be removed.
	@Override
	public Function create(int size, Vector params) {
		return new DoubleSlash((Function) params.get(0));
	}
	/**
	 * <P>On-line: <B>"Change the single / into //"</B>
	 *
	 * <span class="strong">Example:</span>
	 * 
	 * <PRE>doubleSlash("Dog/Barks");</PRE>
	 * <P>will return Dog//Barks.
	 * 
	 * @param input  the input string, the string to be converted (type: String)
	 * 
	 * @return <code>output</code> - string (type String)
	 * 
	 * 
	 */
	
	@Override	
	public Object evaluate(Object data) {
		MESSAGE.printASIS();
		String output = "";
		//int length = 0;
		String input = m_fInput.evaluateAsString(data);
		input=input.trim();
		for ( int i = 0; i < input.length(); ++i ) {
			   char c = input.charAt( i );
			   //System.out.println(" "+i +"="+ c);
			   if (c=='/') {
				   output=output+"//";
			   } else {
				   output=output+c;
			   }
				   
		}


		return "\"" + output + "\"";		
	}
}
