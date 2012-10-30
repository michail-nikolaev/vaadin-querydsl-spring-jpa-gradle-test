package org.nkey.test.vaadin.view.spring;


import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 1:25
 */
public class SpringApplicationServlet extends AbstractApplicationServlet {
    /**
     * Default application bean name in Spring application context.
     */
    private static final String DEFAULT_APP_BEAN_NAME = "application";

    /**
     * Application bean name in Spring application context.
     */
    private String name;

    /**
     * Get and stores in the servlet the application bean's name in the Spring's context. It's expected to be configured
     * as a the servlet &lt;code&gt;init-param&lt;/code&gt; named applicationBeanName. If no param is found, the default
     * is "application".
     *
     * @see AbstractApplicationServlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        String name = config.getInitParameter("applicationBeanName");

        this.name = name == null ? DEFAULT_APP_BEAN_NAME : name;
    }

    /**
     * Get the application bean in Spring's context.
     *
     * @see AbstractApplicationServlet#getNewApplication(HttpServletRequest)
     */
    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        if (wac == null) {

            throw new ServletException("Cannot get an handle on Spring's context. Is Spring running?"
                    + "Check there's an org.springframework.web.context.ContextLoaderListener configured.");
        }

        Object bean = wac.getBean(name);

        if (!(bean instanceof Application)) {

            throw new ServletException("Bean " + name + " is not of expected class Application");
        }

        return (Application) bean;
    }

    /**
     * Get the application class from the bean configured in Spring's context.
     *
     * @see AbstractApplicationServlet#getApplicationClass()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        if (wac == null) {

            throw new ClassNotFoundException("Cannot get an handle on Spring's context. Is Spring running? "
                    + "Check there's an org.springframework.web.context.ContextLoaderListener configured.");
        }

        Object bean = wac.getBean(name);

        if (bean == null) {

            throw new ClassNotFoundException("No application bean found under name " + name);
        }

        return (Class) bean.getClass();
    }
}
