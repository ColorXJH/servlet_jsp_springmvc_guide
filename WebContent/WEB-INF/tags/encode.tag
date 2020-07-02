<%@ attribute name="input" required="true" %>
<%! 
		private String encodeHtmlTag(String tag){
			if(tag==null){
				return null;
			}
			int length=tag.length();
			StringBuilder sb=new StringBuilder(2*length);
			for(int i=0;i<length;i++){
				char c=tag.charAt(i);
				if(c=='<'){
					sb.append("&lt");
				}else if(c=='&'){
					sb.append("&amp");
				}else if(c=='>'){
					sb.append("&gt");
				}else if(c=='"'){
					sb.append("&qout");
				}else if(c==' '){
					sb.append("&nbsp");
				}else{
					sb.append(c);
				}
			}
			return sb.toString();
		}
%>
<%=encodeHtmlTag(input)%>