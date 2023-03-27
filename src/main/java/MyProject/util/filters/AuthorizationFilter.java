package MyProject.util.filters;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = request.getParameter("nickName");
        String name = request.getParameter("lastName");
        String mail = request.getParameter("mail");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        if ((login != null && password != null)
                && (name == null && mail == null && phone == null)
                && isCheck(login, password)) {
            chain.doFilter(request, response);
        }
        if (!isExist(login, name, mail, phone, password)
                || isCheck(login, name, mail, phone, password)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("filterError.html").forward(request, response);
        }
    }

    private boolean findExpression(String expression, String text) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    private boolean isExist(String... strings) {
        for (String s : strings) {
            if (s != null)
                return true;
        }
        return false;
    }

    private boolean isCheck(String... strings) {
        String[] expression = getExpression();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null || !findExpression(expression[i], strings[i])) {
                return false;
            }
        }
        return true;
    }

    private String[] getExpression() {
        String[] expression = new String[5];
        expression[0] = "\\w{3,15}";
        expression[1] = "\\w{3,15}";
        expression[2] = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        expression[3] = "\\+\\d{2}\\d{3}\\d{3}\\d{2}\\d{2}$";
        expression[4] = "\\w{3,15}";
        return expression;
    }
}
