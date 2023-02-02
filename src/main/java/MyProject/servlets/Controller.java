package MyProject.servlets;

import MyProject.Intefaces.intefacesCommand.CommandFactoryInfo;
import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.factory.CommandFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//выполняем все через один сервлет только в зависимости какая команда приходит

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccessRequest(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccessRequest(request, response);
    }

    private void proccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CommandFactoryInfo factory = CommandFactory.getCommandFactory();
//обязательно передаем запрос, т.к в запросе будет нужный action
        CommandInfo command = factory.getCommandInfo(request);
//вытащим имя страницы из выполненной команды
        String page = command.execute(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request,response);

    }
}
