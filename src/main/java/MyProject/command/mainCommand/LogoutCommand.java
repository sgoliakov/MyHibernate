package MyProject.command.mainCommand;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("employee");
        return "controller?action=main";
    }
}
