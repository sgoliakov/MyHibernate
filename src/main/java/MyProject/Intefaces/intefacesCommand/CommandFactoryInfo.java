package MyProject.Intefaces.intefacesCommand;

import jakarta.servlet.http.HttpServletRequest;

public interface CommandFactoryInfo {
    CommandInfo getCommandInfo(HttpServletRequest request);
}
