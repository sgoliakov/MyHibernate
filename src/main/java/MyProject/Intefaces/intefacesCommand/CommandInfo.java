package MyProject.Intefaces.intefacesCommand;

import jakarta.servlet.http.HttpServletRequest;

public interface CommandInfo {
//метод будет возвращать название нужной страницы из request
    String execute(HttpServletRequest request);
}
