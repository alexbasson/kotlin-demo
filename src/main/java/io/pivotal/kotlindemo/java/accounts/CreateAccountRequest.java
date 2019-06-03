package io.pivotal.kotlindemo.java.accounts;

public class CreateAccountRequest {
    private final Long clientId;
    private final String number;
    private final String fullname;
    private final String nickname;
    private final AccountType type;

    public CreateAccountRequest(Long clientId, String number, String fullname, String nickname, AccountType type) {
        this.clientId = clientId;
        this.number = number;
        this.fullname = fullname;
        this.nickname = nickname;
        this.type = type;
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
}
