package se.crisp.edu.java.destruct.example;

import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailMessageTest {

    private static final String SOME_SENDER = "some-sender";
    private static final String SOME_RECIPIENT = "some-recipient";
    private static final String SOME_CONTENT = "some-content";
    private static final String SOME_SUBJECT = "some-subject";
    private static final String SOME_MIME_TYPE = "some-mime-type";

    @Test
    public void withNoMimeType() {
        EmailMessage emailMessage = new EmailMessage(SOME_SENDER, SOME_RECIPIENT, SOME_SUBJECT, SOME_CONTENT);

        String asJson = "{" +
                new EmailMessage.Destructor<String>(emailMessage)
                        .from(s -> str("from", s))
                        .to(s -> str("to", s))
                        .subject(s -> str("subject", s))
                        .content(s -> str("content", s))
                        .mimeType(s -> str("mimeType", s))
                        .stream()
                        .collect(Collectors.joining(","))
                + "\n}";


        System.out.println(asJson);
        assertTrue(asJson.contains("\"from\":\"" + SOME_SENDER + "\""));
        assertTrue(asJson.contains("\"to\":\"" + SOME_RECIPIENT + "\""));
        assertTrue(asJson.contains("\"subject\":\"" + SOME_SUBJECT + "\""));
        assertTrue(asJson.contains("\"content\":\"" + SOME_CONTENT + "\""));
        assertFalse(asJson.contains("\"mimeType\":\""));
    }

    @Test
    public void withMimeType() {
        EmailMessage emailMessage = new EmailMessage(SOME_SENDER, SOME_RECIPIENT, SOME_SUBJECT, SOME_CONTENT
                , SOME_MIME_TYPE);

        String asJson = "{" +
                new EmailMessage.Destructor<String>(emailMessage)
                        .from(s -> str("from", s))
                        .to(s -> str("to", s))
                        .subject(s -> str("subject", s))
                        .content(s -> str("content", s))
                        .mimeType(s -> str("mimeType", s))
                        .stream()
                        .collect(Collectors.joining(","))
                + "\n}";

        System.out.println(asJson);
        assertTrue(asJson.contains("\"from\":\"" + SOME_SENDER + "\""));
        assertTrue(asJson.contains("\"to\":\"" + SOME_RECIPIENT + "\""));
        assertTrue(asJson.contains("\"subject\":\"" + SOME_SUBJECT + "\""));
        assertTrue(asJson.contains("\"content\":\"" + SOME_CONTENT + "\""));
        assertTrue(asJson.contains("\"mimeType\":\"" + SOME_MIME_TYPE + "\""));
    }

    private String str(String label, String s) {
        return String.format("\n\t\"%s\":\"%s\"", label, s);
    }
}
