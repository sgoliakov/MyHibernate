package MyProject.servlets;

import MyProject.Intefaces.intefacesCommand.CommandFactoryInfo;
import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.factory.CommandFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

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
        CommandInfo command = factory.getCommandInfo(request);
        String page = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
