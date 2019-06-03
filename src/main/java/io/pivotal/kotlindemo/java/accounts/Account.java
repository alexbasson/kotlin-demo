package io.pivotal.kotlindemo.java.accounts;

import java.util.Objects;

public class Account {
    private final Long id;
    private final Long clientId;
    private final String number;
    private final String fullname;
    private final String nickname;
    private final AccountType type;

    public Account(Long id, Long clientId, String number, String fullname, String nickname, AccountType type) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
        this.fullname = fullname;
        this.nickname = nickname;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getNumber() {
        return number;
    }

    public String getFullname() {
        return fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public AccountType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(clientId, account.clientId) &&
                Objects.equals(number, account.number) &&
                Objects.equals(fullname, account.fullname) &&
                Objects.equals(nickname, account.nickname) &&
                type == account.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, number, fullname, nickname, type);
    }
}
