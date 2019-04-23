package se.crisp.edu.java.destruct.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

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

    public static class Destructor<T> implements DTo<T>, DSubject<T>, DContent<T>, DMimeType<T>, Destruct<T> {
        List<T> list;
        EmailMessage emailMessage;

        public Destructor(EmailMessage emailMessage) {
            this.emailMessage = emailMessage;
            this.list = new ArrayList<>();
        }

        public DTo<T> from(Function<String, T> fun) {
            list.add(fun.apply(this.emailMessage.from));
            return this;
        }

        public DSubject<T> to(Function<String, T> fun) {
            list.add(fun.apply(this.emailMessage.to));
            return this;
        }

        @Override
        public DContent<T> subject(Function<String, T> fun) {
            list.add(fun.apply(this.emailMessage.subject));
            return this;
        }

        @Override
        public DMimeType<T> content(Function<String, T> fun) {
            list.add(fun.apply(this.emailMessage.content));
            return this;
        }

        @Override
        public Destruct<T> mimeType(Function<String, T> fun) {
            if (this.emailMessage.mimeType != null) {
                list.add(fun.apply(this.emailMessage.mimeType));
            }
            return this;
        }

        public Stream<T> stream() {
            return list.stream();
        }
    }

    interface DTo<T> {
        DSubject<T> to(Function<String, T> fun);
    }

    interface DSubject<T> {
        DContent<T> subject(Function<String, T> fun);
    }

    interface DContent<T> {
        DMimeType<T> content(Function<String, T> fun);
    }

    interface DMimeType<T> {
        Destruct<T> mimeType(Function<String, T> fun);
    }

    interface Destruct<T> {
        Stream<T> stream();
    }
}
