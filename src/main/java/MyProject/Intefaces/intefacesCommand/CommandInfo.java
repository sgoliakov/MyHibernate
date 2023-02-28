package MyProject.Intefaces.intefacesCommand;

import jakarta.servlet.http.HttpServletRequest;

public interface CommandInfo {
    String execute(HttpServletRequest request);
}