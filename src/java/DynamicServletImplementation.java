/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Boga
 */
@WebServlet(urlPatterns = {"/DynamicImplementation/DynamicServletImplementation"})
public class DynamicServletImplementation extends DynamicServlet
{
    protected final Class klass = DynamicServletImplementation.class;
    protected final List<String> getActions = new ArrayList<String>(){{
        add("new"); // Example: New, Edit ...
        add("index"); // Example: New, Edit ...
    }};
    protected final List<String> postActions = new ArrayList<String>(){{
        add("create"); // Example: Create, Update, Delete ...
    }};
    
    protected void getNew() 
            throws ServletException, IOException {
        final_redirect = "New.jsp";
        redirect();
    }
    
    protected void postCreate() 
            throws ServletException, IOException {
        final_redirect = "index.jsp";
        request.setAttribute("message", "After post creation");
        redirect();
    }
    
    @Override
    protected void executeAction(String action, String HTTPAction) 
            throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        List<String> actionList;
        actionList = (HTTPAction.equals("get")) ? getActions : postActions;
        
        if (actionList.contains(action.toLowerCase())) {
            String methodName = HTTPAction + Character.toUpperCase(action.charAt(0)) + action.substring(1);
            Method method = klass.getDeclaredMethod(methodName);
            method.invoke(this, (Object[])null);
        } else {
            Method method = klass.getDeclaredMethod(HTTPAction + "Index");
            method.invoke(this, (Object[])null);
        }
    }
}
