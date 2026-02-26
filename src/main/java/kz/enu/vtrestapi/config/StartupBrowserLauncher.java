package kz.enu.vtrestapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@Component
public class StartupBrowserLauncher {

    @Value("${app.open-browser-on-start:false}")
    private boolean openBrowserOnStart;

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        if (!openBrowserOnStart) {
            return;
        }
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        try {
            Desktop.getDesktop().browse(new URI("http://localhost:8080/"));
        } catch (Exception ignored) {
        }
    }
}
