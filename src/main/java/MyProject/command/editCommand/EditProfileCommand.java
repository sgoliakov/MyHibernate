package MyProject.command.editCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import jakarta.servlet.http.HttpServletRequest;

public class EditProfileCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("updated", "Your page for update data");
        return "editor.jsp";
    }
}
