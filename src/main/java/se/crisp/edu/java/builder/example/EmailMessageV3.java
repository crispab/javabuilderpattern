package se.crisp.edu.java.builder.example;

@SuppressWarnings("WeakerAccess")
public class EmailMessageV3 {

    private String from;
    private String to;
    private String subject;
    private String content;
    private String mimeType;  // optional

    private EmailMessageV3() {}

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

    public static ITo from(String from) {
        return new EmailMessageV3.Builder(from);
    }

    public interface ITo {
        ISubject to(String to);
    }

    public interface ISubject {
        IContent subject(String subject);
    }

    public interface IContent {
        IBuild content(String content);
    }

    public interface IBuild {
        IBuild mimeType(String mimeTypeName);

        EmailMessageV3 build();
    }

    private static class Builder implements ITo, ISubject, IContent, IBuild {
        private EmailMessageV3 instance = new EmailMessageV3();

        public Builder(String from) {
            instance.from = from;
        }

        public ITo from(String from) {
            instance.from = from;
            return this;
        }

        @Override
        public ISubject to(String to) {
            instance.to = to;
            return this;
        }

        @Override
        public IContent subject(String subject) {
            instance.subject = subject;
            return this;
        }

        @Override
        public IBuild content(String content) {
            instance.content = content;
            return this;
        }

        @Override
        public IBuild mimeType(String mimeTypeName) {
            instance.mimeType = mimeTypeName;
            return this;
        }

        @Override
        public EmailMessageV3 build() {
            return instance;
        }
    }
}