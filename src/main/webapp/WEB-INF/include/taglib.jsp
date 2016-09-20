<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctxyy" value="${pageContext.request.contextPath}${fns:getYyPath()}"/>
<c:set var="ctxyh" value="${pageContext.request.contextPath}${fns:getYhPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
