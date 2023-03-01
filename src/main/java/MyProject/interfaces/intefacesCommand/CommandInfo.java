package MyProject.interfaces.intefacesCommand;

import jakarta.servlet.http.HttpServletRequest;

public interface CommandInfo {
    String execute(HttpServletRequest request);
}