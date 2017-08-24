package se.crisp.edu.java.builder.example;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class HttpRequest {
    private String url;
    private Headers headers;
    private Parameters parameters;

    public static RequiredUrl url(String url) {
        return new HttpRequest.Builder(url);
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return headers.get(Headers.ContentType);
    }

    public String getCustomHeader(String customHeader) {
        return headers.get(customHeader);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public interface RequiredUrl {
        RequiredHeaders headers(HttpRequest.Headers headers);
    }

    public interface RequiredHeaders {
        RequiredParameters parameters(HttpRequest.Parameters parameters);
    }

    public interface RequiredParameters {
        HttpRequest build();
    }

    private static class Builder implements RequiredUrl, RequiredHeaders, RequiredParameters {

        private final HttpRequest instance = new HttpRequest();

        public Builder(String url) {
            instance.url = url;
        }

        @Override
        public RequiredHeaders headers(HttpRequest.Headers headers) {
            instance.headers = headers;
            return this;
        }

        @Override
        public RequiredParameters parameters(HttpRequest.Parameters parameters) {
            instance.parameters = parameters;
            return this;
        }

        @Override
        public HttpRequest build() {
            return instance;
        }
    }

    public static class Headers {
        public static final String ContentType = "Content-Type";
        private Map<String, String> headers = new HashMap<>();

        public String get(String field) {
            return headers.getOrDefault(field, "");
        }

        private void put(String field, String value) {
            headers.put(field, value);
        }

        public interface OptionalHeader {

            OptionalHeader header(String field, String value);

            Headers build();

        }

        public static Headers.OptionalHeader header(String field, String value) {
            return new Headers.Builder(field, value);
        }

        public static class Builder implements OptionalHeader {

            Headers headerInstance = new Headers();

            public Builder(String field, String value) {
                headerInstance.put(field, value);
            }

            @Override
            public OptionalHeader header(String field, String value) {
                headerInstance.put(field, value);
                return this;
            }

            @Override
            public Headers build() {
                return headerInstance;
            }

        }

    }

    public static class Parameters {
        private Map<String, String> parameters = new HashMap<>();

        public String get(String key) {
            return parameters.getOrDefault(key, "");
        }

        private void put(String key, String value) {
            parameters.put(key, value);
        }

        public interface OptionalParameter {

            OptionalParameter parameter(String field, String value);

            Parameters build();

        }

        public static Parameters.OptionalParameter parameter(String key, String value) {
            return new Parameters.Builder(key, value);
        }

        public static class Builder implements OptionalParameter {

            Parameters parameterInstance = new Parameters();

            public Builder(String key, String value) {
                parameterInstance.put(key, value);
            }

            @Override
            public OptionalParameter parameter(String key, String value) {
                parameterInstance.put(key, value);
                return this;
            }

            @Override
            public Parameters build() {
                return parameterInstance;
            }

        }

    }
}
