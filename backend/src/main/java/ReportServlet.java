import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;

@WebServlet(
        name = "Report",
        urlPatterns = "/Report"
)
public class ReportServlet extends HttpServlet {
    private static final int SIZE_OF_FREE_STORES = 5;
    private static final String[] freeStores = new String[SIZE_OF_FREE_STORES];
    private static final String KEY="AIzaSyCKGecbdWTOh971l1-9j-ZeQPnMyf2rDjM";
    static{
        freeStores[0]="6115+217th+St+Flushing+NY+11364";
        freeStores[1]="8008+135th+St+Jamaica+NY+11435";
        freeStores[2]="2227+Ryan+St+2FL+Whitestone+NY+11357";
        freeStores[3]="25th+Ave+Flushing+NY+11357";
        freeStores[4]="5066+Deland+Rd+Flushing+MI+48433";
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        String[] answers = (String[])session.getAttribute("answers");
        //String[] addressArr = getAddresses(answers[1]);
        String address="";
        int numOfStore=Integer.MAX_VALUE;
        for(int i=0;i<SIZE_OF_FREE_STORES;++i){
            String id = getAddressID(freeStores[i]);
            int num= numOfStores(answers[1],id,answers[0]);
            System.out.println(num);
            if(num<numOfStore){
                numOfStore=num;
                address=freeStores[i];
            }
        }
        request.setAttribute("address", address);
        request.setAttribute("total", answers[2]);
        request.setAttribute("rent", answers[3]);

        request.getRequestDispatcher("report.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String getAddressID(String address) throws IOException{
        String requestUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+address+"%&inputtype=textquery&fields=geometry&locationbias=circle:2000@40.736208,%-73.815959&key="+KEY;
        URL googleMapRequest = new URL(requestUrl);
        JSONTokener tokener = new JSONTokener(googleMapRequest.openStream());
        JSONObject root = new JSONObject(tokener);
        JSONArray arr = root.getJSONArray("candidates");
        JSONObject candidates = arr.getJSONObject(0);
        JSONObject geometry = (JSONObject) candidates.get("geometry");
        JSONObject location = geometry.getJSONObject("location");
        return location.get("lat")+","+location.get("lng");
    }
//    private String[] getAddresses(String category) throws IOException{
//        String requestUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+category+"%new%york%&inputtype=textquery&fields=formatted_address,name,rating&locationbias=circle:2000@40.736208,%-73.815959&key="+KEY;
//        URL googleMapRequest = new URL(requestUrl);
//        JSONTokener tokener = new JSONTokener(googleMapRequest.openStream());
//        JSONObject root = new JSONObject(tokener);
//        JSONArray arr = root.getJSONArray("candidates");
//        int size =arr.length();
//        String [] addressArr = new String[size];
//        for(int i=0;i<size;i++){
//            JSONObject candidates = arr.getJSONObject(i);
//            String address = candidates.get("formatted_address").toString();
//            address=address.replaceAll("\\s+", "+")
//                    .replace(",", "+");
//            addressArr[i]=address;
//        }
//        return addressArr;
//    }
    private int numOfStores(String category,String addressId,String block)throws IOException{
        System.out.println(addressId);
        System.out.println(category);
        String requestUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+addressId+"2&radius=2500&type="+category+"&keyword=cruise&key="+KEY;
        URL googleMapRequest = new URL(requestUrl);
        JSONTokener tokener = new JSONTokener(googleMapRequest.openStream());
        JSONObject root = new JSONObject(tokener);
        JSONArray arr = root.getJSONArray("results");
        return arr.length();
    }
}
