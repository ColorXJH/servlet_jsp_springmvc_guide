<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="simple" tagdir="/WEB-INF/tags" %>
a static file contain the static _file,html,嘻嘻<br/>
<%@ include file="static_file.html"%>
<br/>
the second is dynamic file<br/>
<%@ include file="dynamic_file.tagf" %>
<br/>
<simple:firstTag/>
<br/>
<simple:varDemo>
	in longDate:${longDate }<br/>
	in shortDate:${shortDate }<br/>
</simple:varDemo>
