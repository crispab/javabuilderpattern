package se.crisp.edu.java.builder.example;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class EmailMessageV2Test {

    private static final String SOME_SENDER = "some.sender@example.com";
    private static final String SOME_RECEIVER = "some.receiver@example.com";
    private static final String SOME_SUBJECT = "Some subject for the email";
    private static final String SOME_CONTENT = "Some email content.";
    private static final String SOME_MIME_TYPE = "some/mime-type";

    @Test
    public void create_message_with_default_mime_type() {

        EmailMessageV2 emailMessageV2 = EmailMessageV2.builder()
                .from(SOME_SENDER)
                .to(SOME_RECEIVER)
                .subject(SOME_SUBJECT)
                .content(SOME_CONTENT)
                .build();

        assertThat(emailMessageV2.getFrom(), is(SOME_SENDER));
        assertThat(emailMessageV2.getTo(), is(SOME_RECEIVER));
        assertThat(emailMessageV2.getSubject(), is(SOME_SUBJECT));
        assertThat(emailMessageV2.getContent(), is(SOME_CONTENT));
        assertNull(emailMessageV2.getMimeType());
    }

    @Test
    public void create_message_with_specific_mime_type() {

        EmailMessageV2 emailMessageV2 = EmailMessageV2.builder()
                .from(SOME_SENDER)
                .to(SOME_RECEIVER)
                .subject(SOME_SUBJECT)
                .content(SOME_CONTENT)
                .mimeType(SOME_MIME_TYPE)
                .build();

        assertThat(emailMessageV2.getMimeType(), is(SOME_MIME_TYPE));
    }

    @Test(expected = AssertionError.class)
    public void handle_missing_receiver() throws Exception {

        EmailMessageV2.builder()
                .from(SOME_SENDER)
                .subject(SOME_SUBJECT)
                .content(SOME_CONTENT)
                .mimeType(SOME_MIME_TYPE)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void handle_missing_from() throws Exception {

        EmailMessageV2.builder()
                .to(SOME_RECEIVER)
                .subject(SOME_SUBJECT)
                .content(SOME_CONTENT)
                .mimeType(SOME_MIME_TYPE)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void handle_missing_subject() throws Exception {

        EmailMessageV2.builder()
                .from(SOME_SENDER)
                .to(SOME_RECEIVER)
                .content(SOME_CONTENT)
                .mimeType(SOME_MIME_TYPE)
                .build();
    }


    @Test(expected = AssertionError.class)
    public void handle_missing_content() throws Exception {

        EmailMessageV2.builder()
                .from(SOME_SENDER)
                .to(SOME_RECEIVER)
                .subject(SOME_SUBJECT)
                .mimeType(SOME_MIME_TYPE)
                .build();
    }
}