package com.osama.chatting.Servlet;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.CommandFactory;
import com.osama.chatting.commands.Page;
import com.osama.chatting.utils.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.osama.chatting.utils.MessageManager.NONE_MESSAGE_KEY;
import static com.osama.chatting.commands.ActionCommand.MESSAGE_ATTRIBUTE;

public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Page  page;
        CommandFactory commandFactory = new CommandFactory();
        ActionCommand actionCommand = commandFactory.defineCommand(request);
        System.out.println(actionCommand.getClass().getName());
        page =  actionCommand.execute(request);
        boolean isDirect = page.isDirect();
        if (isDirect){
            redirect(page, request, response);
        }
        else {
            forward(page, request, response);
        }
    }
    private void redirect(Page page, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = page.getUrlPage();
        response.sendRedirect(request.getContextPath() + url);
    }

    private void forward(Page page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = page.getUrlPage();
        String message = page.getMessageKey();
        if (!message.equals(NONE_MESSAGE_KEY)){
            request.setAttribute(MESSAGE_ATTRIBUTE, MessageManager.getProperty(message));
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
