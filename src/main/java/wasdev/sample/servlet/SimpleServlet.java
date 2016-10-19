/******************************************************************************
 * Title: POC of Watson API Encapsulation: Language Translation
 * Author: Clark Lin
 * History:
 * Version    Date               Comment
 * V1.0       2016/10/17         First working version for Java Liberty from new UI
 ******************************************************************************/

package wasdev.sample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.language_translation.v2.model.*;
import com.ibm.watson.developer_cloud.language_translation.v2.*;

import com.ibm.watson.developer_cloud.conversation.v1.*;
import com.ibm.watson.developer_cloud.conversation.v1.model.*;
import com.ibm.watson.developer_cloud.conversation.v1_experimental.model.*;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Assume content in source language is passed through URL
    	String param = request.getParameter("content");
        getTranslatedResult(param);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result.getFirstTranslation());
    }
    
    private static LanguageTranslation service = new LanguageTranslation();
    private static TranslationResult result;
    
    public static void getTranslatedResult(String content) {
    	// System.getenv("VCAP_SERVICES");
    	// Hardcode language translation API URI and cridential
    	service.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
    	service.setUsernameAndPassword("d02a80d2-fb2a-4941-9d74-7ed0e72541c4", "i6YdoXRKCWEz");
    	// Invoke Watson API to translate
    	result = service.translate(content, Language.ENGLISH, Language.SPANISH).execute();
    }
    
}

