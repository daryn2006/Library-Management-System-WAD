package kz.enu.vtrestapi.config;

import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.repository.AppUserRepository;
import kz.example.lms.service.LmsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ReaderSyncInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReaderSyncInitializer.class);

    private final AppUserRepository appUserRepository;
    private final LmsDataService lmsDataService;

    public ReaderSyncInitializer(AppUserRepository appUserRepository, LmsDataService lmsDataService) {
        this.appUserRepository = appUserRepository;
        this.lmsDataService = lmsDataService;
    }

    @Override
    public void run(String... args) {
        try {
            for (AppUser user : appUserRepository.findAll()) {
                if (!"USER".equalsIgnoreCase(user.getRole())) {
                    continue;
                }
                if (!StringUtils.hasText(user.getEmail())) {
                    continue;
                }
                if (lmsDataService.findMemberByEmail(user.getEmail()) != null) {
                    continue;
                }
                String displayName = StringUtils.hasText(user.getFullName()) ? user.getFullName() : user.getUsername();
                if (!StringUtils.hasText(displayName)) {
                    continue;
                }
                lmsDataService.createMember(displayName, user.getEmail(), null, 1);
            }
        } catch (Exception ex) {
            log.warn("Reader sync skipped because startup data was invalid", ex);
        }
    }
}
