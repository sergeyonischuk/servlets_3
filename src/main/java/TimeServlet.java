import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import java.io.IOException;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        String timezone = req.getParameter("timezone");
        resp.getWriter().write(getTimeAndUTC(timezone));
        resp.getWriter().close();
    }

    public String getTimeAndUTC(String timezone) {
        DateTime dateTime = new DateTime().withZone(DateTimeZone.forID("GMT"));
        dateTime = getDateWithUtc(getUTCDigit(timezone), dateTime);
        return dateTime.toString("yyyy-MM-dd HH:mm:ss") + " " + timezone;
    }

    private int getUTCDigit(String utcParameter) {
        if (utcParameter.contains("-")) {
            return Integer.parseInt(utcParameter.substring((utcParameter.indexOf("-"))));
        } if (utcParameter.contains("+")) {
            return Integer.parseInt(utcParameter.substring((utcParameter.indexOf(utcParameter.substring(4)))));
        }
        else return 0;
    }

    private DateTime getDateWithUtc(int utc, DateTime dateTime) {
            return dateTime.plusHours(utc);
    }
}