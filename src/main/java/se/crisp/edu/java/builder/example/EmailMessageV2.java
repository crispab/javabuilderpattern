package se.crisp.edu.java.builder.example;

@SuppressWarnings("WeakerAccess")
public class EmailMessageV2 {
    private  String from;
    private  String to;
    private  String subject;
    private  String content;
    private  String mimeType;  // optional

    private EmailMessageV2() {}

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static Builder builder() {
        return new EmailMessageV2.Builder();
    }

    public static class Builder {
        private EmailMessageV2 instance = new EmailMessageV2();

        private Builder() {
        }

        public Builder from(String from) {
            instance.from = from;
            return this;
        }

        public Builder to(String to) {
            instance.to = to;
            return this;
        }

        public Builder subject(String subject) {
            instance.subject = subject;
            return this;
        }

        public Builder content(String content) {
            instance.content = content;
            return this;
        }

        public Builder mimeType(String mimeTypeName) {
            instance.mimeType = mimeTypeName;
            return this;
        }

        public EmailMessageV2 build() {
            assert instance.from != null;
            assert instance.to != null;
            assert instance.subject != null;
            assert instance.content != null;

            return instance;
        }
    }

}
