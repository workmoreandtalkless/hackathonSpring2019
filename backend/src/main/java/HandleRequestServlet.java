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
    private String[] answers = new String[4];
    private static String[] questions = new String[4];
    private static String[][] options = new String[4][];
    static{
        initQuestions();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value =Integer.parseInt(request.getParameter("stage"));
        String ans = request.getParameter("answer");
        if(value!=0) answers[value-1]=ans;

        HttpSession session = request.getSession();
        session.setAttribute("answers", answers);
        if(value<4){
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
        questions[0]="Choose Your Excepted Location: ";
        questions[1]="Choose Your Excepted category:";
        questions[2]="What's the total amount do you want to invest?";
        questions[3]="How much do you want to pay rent?";
        options[0]=new String[4];
        options[0][0]="Astoria";
        options[0][1]="Elmhurst";
        options[0][2]="Woodside";
        options[0][3]="Flushing";

        options[1]=new String[2];
        options[1][0]="Coffee";
        options[1][1]="Gym";

        options[2]=new String[6];
        options[2][0]="Less than $10,000";
        options[2][1]="$10,000 - $100,000 ";
        options[2][2]="$100,000 - $300,000";
        options[2][3]="$300,000 - $500,000";
        options[2][4]="$500,000 - $1,000,000";
        options[2][5]="Over $100,000";

        options[3]= new String[3];
        options[3][0]="Less than $10,000";
        options[3][1]="$10,000 - $20,000";
        options[3][2]="Over $20,000";

    }
}
