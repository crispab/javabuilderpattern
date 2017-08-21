package se.crisp.edu.java.builder.example;

@SuppressWarnings("WeakerAccess")
public class EmailMessage {

    private final String from;
    private final String to;
    private final String subject;
    private final String content;
    private final String mimeType;  // optional

    public EmailMessage(String from, String to, String subject, String content) {
        this(from, to, subject, content, null);
    }

    public EmailMessage(String from, String to, String subject, String content, String mimeType) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.mimeType = mimeType;
    }

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

}
