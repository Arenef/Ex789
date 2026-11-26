package util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
    public String getCookieValue(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equalsIgnoreCase(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie c = new Cookie(name, value);
        c.setMaxAge(maxAge);
        c.setPath("/");
        response.addCookie(c);
    }

    public void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie c = new Cookie(cookieName, ""); // ghi đè cookie cũ
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
    }
}
