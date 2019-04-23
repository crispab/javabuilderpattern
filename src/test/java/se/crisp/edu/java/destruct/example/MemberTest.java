package se.crisp.edu.java.destruct.example;

import org.junit.Test;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class MemberTest {

    public static final UUID UUID = java.util.UUID.randomUUID();
    public static final String SOME_USER_NAME = "some-user-name";
    public static final int SOME_YEAR = 1942;

    @Test
    public void buildAndDestroy() {
        Member member = Member
                .id(UUID)
                .userName(SOME_USER_NAME)
                .yob(SOME_YEAR)
                .build();

        String asJson = "{\n" +
                new Member.Destructor<String>(member)
                        .id(s -> str("id", s.toString()))
                        .userName(s -> str("userName", s))
                        .yob(y -> str("birthYear", y))
                        .stream()
                        .collect(Collectors.joining(",")) +
                "\n}";


        System.out.println(asJson);
        assertTrue(asJson.contains("\"id\": \"" + UUID + "\""));
        assertTrue(asJson.contains("\"userName\": \"" + SOME_USER_NAME + "\""));
        assertTrue(asJson.contains("\"birthYear\": " + SOME_YEAR));

    }

    private String str(String label, String s) {
        return String.format("\n\t\"%s\": \"%s\"", label, s);
    }

    private String str(String label, Integer s) {
        return String.format("\n\t\"%s\": %d", label, s);
    }
}