package se.crisp.edu.java.builder.example;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class EmailMessageV3Test {

    private static final String SOME_SENDER = "some.sender@example.com";
    private static final String SOME_RECEIVER = "some.receiver@example.com";
    private static final String SOME_SUBJECT = "Some subject for the email";
    private static final String SOME_CONTENT = "Some email content.";
    private static final String SOME_MIME_TYPE = "some/mime-type";

    @Test
    public void create_message_with_default_mime_type() {

        EmailMessageV3 emailMessageV3 = EmailMessageV3
                .from(SOME_SENDER)
                .to(SOME_RECEIVER)
                .subject(SOME_SUBJECT)
                .content(SOME_CONTENT)
                .build();

        assertThat(emailMessageV3.getFrom(), is(SOME_SENDER));
        assertThat(emailMessageV3.getTo(), is(SOME_RECEIVER));
        assertThat(emailMessageV3.getSubject(), is(SOME_SUBJECT));
        assertThat(emailMessageV3.getContent(), is(SOME_CONTENT));
        assertNull(emailMessageV3.getMimeType());
    }

    @Test
    public void create_message_with_specific_mime_type() {

        EmailMessageV3 emailMessageV3 = EmailMessageV3
                .from(SOME_SENDER)
                .to(SOME_RECEIVER)
                .subject(SOME_SUBJECT)
                .content(SOME_CONTENT)
                .mimeType(SOME_MIME_TYPE)
                .build();

        assertThat(emailMessageV3.getMimeType(), is(SOME_MIME_TYPE));
    }

}