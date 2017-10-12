package ua.goit.applications;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.goit.configuration.ModelConfiguration;
import ua.goit.configuration.SpringSecurityConfiguration;
import ua.goit.configuration.WebConfiguration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

/**
 * Main entry point to Spring MVC application.
 *
 * Also it register {@link org.springframework.web.servlet.DispatcherServlet} that handle
 * all requests.
 */
public class WebMvcApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ModelConfiguration.class, SpringSecurityConfiguration.class} ;//{,  }
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        //TODO 3. прописать root
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        // upload temp file will put here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }
}
