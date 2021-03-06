package com.wang.novelweb.Config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品类目
 *
 * @author wlh
 * @date 2019-11-12
 */
public class MyRememberFilter extends FormAuthenticationFilter {
    protected boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && subject.isRemembered()) {
            if (subject.getSession().getAttribute("username") == null && subject.getPrincipal() != null) {
                subject.getSession().setAttribute("username", subject.getPrincipal());
            }

        }

        return subject.isAuthenticated() || subject.isRemembered();
    }
}
