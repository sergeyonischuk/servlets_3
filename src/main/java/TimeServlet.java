import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/time")
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
        String s = "";
        if (utcParameter.contains("-")) {
            s = utcParameter.substring((utcParameter.indexOf("-")));
        } else {
            s = utcParameter.substring((utcParameter.indexOf(utcParameter.substring(4))));
        }
        return Integer.parseInt(s);
    }

    private DateTime getDateWithUtc(int utc, DateTime dateTime) {
            return dateTime.plusHours(utc);
    }
}