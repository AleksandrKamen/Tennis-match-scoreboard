package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPUtil {
    private String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";
    public String getPath(String jspName){
        return String.format(JSP_FORMAT,jspName);
    }
}
