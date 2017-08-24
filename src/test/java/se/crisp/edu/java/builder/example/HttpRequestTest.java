package se.crisp.edu.java.builder.example;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static se.crisp.edu.java.builder.example.HttpRequest.Headers.ContentType;

public class HttpRequestTest {

    private static final String SOME_URL = "http://some.url.info";
    private static final String SOME_MIME_TYPE = "application/x-www-form-urlencoded";
    private static final String SOME_CUSTOM_VALUE = "someCustomValue";
    private static final String SOME_CUSTOM_HEADER = "X-Some-Custom-Header";
    private static final String SOME_KEY = "some key";
    private static final String SOME_KEY_2 = "some key 2";
    private static final String SOME_VALUE = "some value";
    private static final String SOME_VALUE_2 = "some value 2";

    @Test
    public void demonstrate_request_with_headers() throws Exception {

        HttpRequest request = HttpRequest
                .url(SOME_URL)
                .headers(HttpRequest.Headers
                        .header(ContentType, SOME_MIME_TYPE)
                        .header(SOME_CUSTOM_HEADER, SOME_CUSTOM_VALUE)
                        .build()
                )
                .parameters(HttpRequest.Parameters
                        .parameter(SOME_KEY, SOME_VALUE)
                        .parameter(SOME_KEY_2, SOME_VALUE_2)
                        .build())
                .build();

        assertThat(request.getUrl(), is(SOME_URL));
        assertThat(request.getContentType(), is(SOME_MIME_TYPE));
        assertThat(request.getCustomHeader(SOME_CUSTOM_HEADER), is(SOME_CUSTOM_VALUE));
        assertThat(request.getParameter(SOME_KEY), is(SOME_VALUE));
    }
}
