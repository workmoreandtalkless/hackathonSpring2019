<%--
  Created by IntelliJ IDEA.
  User: steven
  Date: 2019-04-05
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Report</title>
    </head>

    <body>
        <% String address= (String)request.getAttribute("address");%>
        <section id="contact" class="map">
            <iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=<%=address%>&amp;aq=0&amp;oq=QueensCollege&amp;ie=UTF8&amp;hq=<%=address%>&amp;t=m&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
            <br />
        </section>
    </body>
</html>
