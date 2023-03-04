
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String timeZone = req.getParameter("timezone");
        String regex = "^UTC([- ])([1-9]|1[0-2])$";
        if (timeZone.matches(regex)) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(400);
            res.getWriter().write("invalid timezone");
            res.getWriter().close();
        }
    }
}
