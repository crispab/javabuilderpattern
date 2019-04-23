package se.crisp.edu.java.destruct.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

public class Member {
    private UUID id;
    private String userName;
    private int yob;

    static IUserName id(UUID uuid) {
        return new Builder(uuid);
    }

    static class Builder implements IUserName, IYob, IBuild {

        private final Member member;

        public Builder(UUID uuid) {
            this.member = new Member();
            this.member.id = uuid;
        }

        @Override
        public IYob userName(String userName) {
            this.member.userName = userName;
            return this;
        }

        @Override
        public IBuild yob(int yob) {
            this.member.yob = yob;
            return this;
        }

        @Override
        public Member build() {
            return this.member;
        }
    }

    interface IUserName {
        IYob userName(String userName);
    }

    interface IYob {
        IBuild yob(int yob);
    }

    interface IBuild {
        Member build();
    }

    public static class Destructor<T> implements DId<T>, DUserName<T>, DYob<T>, Destruct<T> {
        private Member member;
        private List<T> list;

        public Destructor(Member member) {
            this.member = member;
            this.list = new ArrayList<>();
        }

        @Override
        public Destruct<T> yob(Function<Integer, T> function) {
            list.add(function.apply(this.member.yob));
            return this;
        }

        @Override
        public DYob<T> userName(Function<String, T> function) {
            list.add(function.apply(this.member.userName));
            return this;
        }

        @Override
        public DUserName<T> id(Function<UUID, T> function) {
            list.add(function.apply(this.member.id));
            return this;
        }

        @Override
        public Stream<T> stream() {
            return list.stream();
        }

        @Override
        public List<T> list() {
            return this.list;
        }
    }

    interface DId<T> {
        DUserName<T> id(Function<UUID, T> function);
    }
    interface DUserName<T> {
        DYob<T> userName(Function<String, T> function);
    }
    interface DYob<T> {
        Destruct<T> yob(Function<Integer, T> function);
    }

    interface Destruct<T> {
        Stream<T> stream();
        List<T> list();
    }
}
