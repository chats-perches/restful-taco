package los.campesinos.resttaco.util;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * demented work around for the illegal ';' in the JSESSIONID redirect URL
 * TODO: scrap this and the web.xml, if possible
 * */

public class DisableUrlSessionFilter implements Filter {

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException
    {
        if (!(request instanceof HttpServletRequest))
        {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.isRequestedSessionIdFromURL())
        {
            HttpSession session = httpRequest.getSession();
            if (session != null) session.invalidate();
        }

        HttpServletResponseWrapper wrappedResponse
                = new HttpServletResponseWrapper(httpResponse)
        {
           // public String encodeRedirectUrl(String url) { return url; }
            public String encodeRedirectURL(String url) { return url; }
           // public String encodeUrl(String url) { return url; }
            public String encodeURL(String url) { return url; }
        };

        chain.doFilter(request, wrappedResponse);

    }

    public void init(FilterConfig config)
            throws ServletException {}

    public void destroy() {}
}