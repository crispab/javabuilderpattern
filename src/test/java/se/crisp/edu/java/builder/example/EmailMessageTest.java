package se.crisp.edu.java.builder.example;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class EmailMessageTest {

    private static final String SOME_SENDER = "some.sender@example.com";
    private static final String SOME_RECEIVER = "some.receiver@example.com";
    private static final String SOME_SUBJECT = "Some subject for the email";
    private static final String SOME_CONTENT = "Some email content.";
    private static final String SOME_MIME_TYPE = "some/mime-type";

    @Test
    public void create_message_with_default_mime_type() {

        EmailMessage emailMessage = new EmailMessage(SOME_SENDER, SOME_RECEIVER, SOME_SUBJECT, SOME_CONTENT);

        assertThat(emailMessage.getFrom(), is(SOME_SENDER));
        assertThat(emailMessage.getTo(), is(SOME_RECEIVER));
        assertThat(emailMessage.getSubject(), is(SOME_SUBJECT));
        assertThat(emailMessage.getContent(), is(SOME_CONTENT));
        assertNull(emailMessage.getMimeType());
    }

    @Test
    public void create_message_with_specific_mime_type() {

        EmailMessage emailMessage = new EmailMessage(SOME_SENDER, SOME_RECEIVER, SOME_SUBJECT, SOME_CONTENT,
                SOME_MIME_TYPE);

        assertThat(emailMessage.getMimeType(), is(SOME_MIME_TYPE));
    }


}