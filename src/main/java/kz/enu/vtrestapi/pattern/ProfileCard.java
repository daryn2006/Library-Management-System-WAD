package kz.enu.vtrestapi.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileCard {

    private final String title;
    private final String subtitle;
    private final String avatarUrl;
    private final List<String> chips;

    private ProfileCard(Builder builder) {
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.avatarUrl = builder.avatarUrl;
        this.chips = List.copyOf(builder.chips);
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<String> getChips() {
        return Collections.unmodifiableList(chips);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private String subtitle;
        private String avatarUrl;
        private final List<String> chips = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder addChip(String chip) {
            if (chip != null && !chip.isBlank()) {
                chips.add(chip);
            }
            return this;
        }

        public ProfileCard build() {
            return new ProfileCard(this);
        }
    }
}
