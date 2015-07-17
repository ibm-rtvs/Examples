package com.ibm.greenhat.examples.customfunctions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import com.ghc.ghTester.expressions.Function;
/**
 * @author Marc van Lint
 * <BR>Legal Noticen and Source can be obtained from http://business.vanlint5.nl
 */
	public class FormatSDF extends Function  {

		private Function m_fTime = null;
		private Function m_fFormat = null;


		public FormatSDF(){
		}
		protected FormatSDF(Function f1,Function f2) {
			m_fTime = f1;
			m_fFormat = f2;
		}

		@SuppressWarnings("rawtypes")
		// TODO : SuppressWarnings should be removed.
		@Override
		public Function create(int size, Vector params) {
					return new FormatSDF((Function) params.get(0),(Function) params.get(1));
		}
		
		/**
		 * <P>On-line: <B>"Provide the formatted time based on provided milliseconds"</B>
		 * <P>Good to know:
		 * <PRE>
		 * tags["value"]=1423980298302983L
		 * tags["value"].toString will give 1.42332E12
		 * tags["value"].valueOf will give the actual value
		 * ""+tags["value"].valueOf will return the actual value of in string type
		 * </PRE>
		 * 
		 * <span class="strong">Example:</span>
		 * 
		 * <PRE>
	     * tags["timeMS"]=1423980298302983L
		 * tags["timeMSstr"]=""+tags["timeMS"];
		 * formatMsDate(tags["timeMSstr"],"yyyy-MM-dd");
		 * </PRE>
		 * <P>will return "2014-12-12"
		 * 
		 * @param timeMSstr  the number of milliseconds (from 1970-01-01) (type: String)
		 * @param formatStr  the SimpleDateFormat string (type: String)
		 * 
		 * @return <code>value</code> - the formatted Date/Time (type String)
		 * 
		 * 
		 */
		@Override	
		public Object evaluate(Object data) {
			MESSAGE.printASIS();
			System.out.println("formatMs2Date");
			//long millis=1418408883924L - about now;
			long millis=0;
			String timeInMs = m_fTime.evaluateAsString(data);
			String formatStr = m_fFormat.evaluateAsString(data);
			millis=Long.parseLong(timeInMs);
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



