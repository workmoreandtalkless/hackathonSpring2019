import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "HandleRequestServlet",
        urlPatterns = "/HandleRequestServlet"
)
public class HandleRequestServlet extends HttpServlet {
    private String[] answers = new String[3];
    private static String[] questions = new String[3];
    private static String[][] options = new String[3][];
    {
        initQuestions();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value =Integer.parseInt(request.getParameter("stage"));
        String ans = request.getParameter("answer");
        if(value!=0) answers[value-1]=ans;
        HttpSession session = request.getSession();
        session.setAttribute("answers", answers);
        if(value<3){
            request.setAttribute("stage", value+1);
            request.setAttribute("question", questions[value]);
            request.setAttribute("options", options[value]);
            request.getRequestDispatcher("survey.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("Report").forward(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private static void initQuestions(){
        questions[0]="Which block do you want?";
        questions[1]="What's the total amount do you want to invest?";
        questions[2]="How much do you want to pay rent?";
        options[0]=new String[3];
        options[0][0]="Flushing";
        options[0][1]="Bayside";
        options[0][2]="Woodside";
        options[1]=new String[6];
        options[1][0]="Less than $10,000";
        options[1][1]="$10,000 - $100,000 ";
        options[1][2]="$100,000 - $300,000";
        options[1][3]="$300,000 - $500,000";
        options[1][4]="$500,000 - $1,000,000";
        options[1][5]="Over $100,000";

        options[2]= new String[3];
        options[2][0]="Less than $10,000";
        options[2][1]="$10,000 - $20,000";
        options[2][2]="Over $20,000";

    }
}