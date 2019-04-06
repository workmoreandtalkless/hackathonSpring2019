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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <style>

        #map{
            margin:20px auto;
        }
        #mapcontainer{
            margin:10px auto;
            padding:10px;
        }
        #box{
            width:50%;
        }
    </style>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="index.jsp">PerfectStore</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp#services">Services</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp#about">About</a>
                    </li>
                    <li class="nav-item">
                        <form action="HandleRequestServlet">
                            <input type="hidden" name="stage" value="0">
                            <input type="submit" class="btn btn-dark btn-xl js-scroll-trigger" value="Searcher">
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container" id="box">
            <div id="mapcontainer">
                <% String address= (String)request.getAttribute("address");%>
                <section >
                    <iframe id="map" width="100%" height="33%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=<%=address%>&amp;aq=0&amp;oq=QueensCollege&amp;ie=UTF8&amp;hq=<%=address%>&amp;t=m&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
                    <br>
                </section>
            </div>
<%--            <section>--%>
<%--                <iframe src="googlechart.html" width="50%" height="50%" frameborder="0" scrolling="no" marginheight="0"></iframe>--%>
<%--            </section>--%>
            <div id="columnchart_material" style="width: 900px; height: 500px;"></div>
        </div>

    </body>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1.1", {packages:["bar"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Location', 'Rent (*$1000)', 'Same type Business number',],
                ['location 1', 6, 3],
                ['location 2', 11, 5],
                ['location 3', 2.2, 2],
                ['location 4', 1, 0]
            ]);

            var options = {
                chart: {
                    title: 'Business Start-up Analysis report',
                    subtitle: 'Location and Competition:',
                }
            };

            var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

            chart.draw(data, options);
        }
    </script>
</html>
