package se.crisp.edu.java.builder.example;

@SuppressWarnings("WeakerAccess")
public class EmailMessageV3Required {

    private String from;
    private String to;
    private String subject;
    private String content;
    private String mimeType;  // optional

    private EmailMessageV3Required() {}

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

    public static RequiredFrom from(String from) {
        return new EmailMessageV3Required.Builder(from);
    }

    public interface RequiredFrom {
        RequiredTo to(String to);
    }

    public interface RequiredTo {
        RequiredSubject subject(String subject);
    }

    public interface RequiredSubject {
        RequiredContent content(String content);
    }

    public interface RequiredContent {
        OptionalMimeType mimeType(String mimeTypeName);

        EmailMessageV3Required build();
    }

    public interface OptionalMimeType {
        EmailMessageV3Required build();
    }

    private static class Builder implements RequiredFrom, RequiredTo, RequiredSubject, RequiredContent, OptionalMimeType {
        private EmailMessageV3Required instance = new EmailMessageV3Required();

        public Builder(String from) {
            instance.from = from;
        }

        public RequiredFrom from(String from) {
            instance.from = from;
            return this;
        }

        @Override
        public RequiredTo to(String to) {
            instance.to = to;
            return this;
        }

        @Override
        public RequiredSubject subject(String subject) {
            instance.subject = subject;
            return this;
        }

        @Override
        public RequiredContent content(String content) {
            instance.content = content;
            return this;
        }

        @Override
        public OptionalMimeType mimeType(String mimeTypeName) {
            instance.mimeType = mimeTypeName;
            return this;
        }

        @Override
        public EmailMessageV3Required build() {
            return instance;
        }
    }
}