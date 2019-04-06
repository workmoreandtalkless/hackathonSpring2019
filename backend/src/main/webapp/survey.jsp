<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Suvery</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
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
        <div class="container">

            <form action="HandleRequestServlet" method="POST" id="question">
                <%
                    String question = (String) request.getAttribute("question");
                    String[] options = (String[]) request.getAttribute("options");
                %>
                <h2><%=question%></h2>
                <div id="options">
                <%
                    int size = options.length;
                    for(int i =0;i<size;++i){%>

                        <input name="answer" type="radio" value="<%=options[i]%>" class="option"
                               <%
                            if(i==0) %>checked<%
                            %>
                        >
                        <%=options[i]%><br>
                 <%   }
                %>
                </div>
                <input type="hidden" value="<%=request.getAttribute("stage")%>" name="stage">
                <br>
                <input type="submit" value="next" class="btn-success">
            </form>

        </div>
    </body>
</html>