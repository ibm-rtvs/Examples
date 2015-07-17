package com.ibm.greenhat.examples.customfunctions;

import java.io.File;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
public class Info extends Function {

	public Info(){
	}
	
	@SuppressWarnings("rawtypes")

	@Override
	public Function create(int size, Vector params) {
		return new Info();
	}
	/**
	 * <P>On-line: <B>Print Version Information</B>
	 *
	 * <span class="strong">Example:</span>
	 * 
	 * <PRE>intLenghtOfTimmedString = Info();</PRE>
	 * <P>will return a lot of text.
	 * 
	 * @param input  none
	 * 
	 * @return <code>length</code> - the length of the trimmed string (type int)
	 * 
	 * 
	 */
	
	@Override	
	public Object evaluate(Object data) {
		MESSAGE.printASIS();
		System.out.println("Code developed by Marc van Lint");
		System.out.println("Code is proved on as-is basis. It can be re-use under the GPL license.");
		System.out.println("Take a look at http://business.vanlint5.nl");
		File dir1 = new File (".");
	    File dir2 = new File ("..");
	     try {
	       System.out.println ("Current dir : " + dir1.getCanonicalPath());
	       System.out.println ("Parent  dir : " + dir2.getCanonicalPath());
	       }
	     catch(Exception e) {
	       e.printStackTrace();
	       };
        //http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
        System.out.println ("File Separator : " + System.getProperty("file.separator"));
        System.out.println ("Path Separator : " + System.getProperty("path.separator"));
        System.out.println ("Operating System Architecture: " + System.getProperty("os.arch"));
        System.out.println ("Operating System Name: " + System.getProperty("os.name"));
        System.out.println ("User home: " + System.getProperty("user.home"));
        System.out.println ("User name: " + System.getProperty("user.name"));
		return "";		
	}
}
