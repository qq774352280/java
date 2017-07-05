public class IKAnalyzerUtil {
	
	public static List<String> participles(String s){
		if("".equals(s)){
			return null;
		}
		IKAnalyzer ana=new IKAnalyzer(true);
		
		List<String> list = new ArrayList<String>();
		StringReader reader=new StringReader(s);
		
		//分词  
		TokenStream ts=ana.tokenStream("", reader);
		CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
		//遍历分词数据  
		try {
			while(ts.incrementToken()){  
				list.add(term.toString());
			}
		} catch (IOException e) {
		}
		reader.close();
		return list;
	}

}
