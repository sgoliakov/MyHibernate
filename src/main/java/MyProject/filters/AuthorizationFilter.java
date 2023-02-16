package MyProject.filters;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = request.getParameter("firstName");
        String password = request.getParameter("password");
        if ((login == null && password == null) || (login.length() > 3
                && password.length() > 5 && findExpression("[0-9]", password))) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("filterError.html").forward(request, response);
        }
    }

    private boolean findExpression(String expression, String text) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
