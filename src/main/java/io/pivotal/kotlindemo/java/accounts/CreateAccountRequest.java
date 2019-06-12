package io.pivotal.kotlindemo.java.accounts;

public class CreateAccountRequest {
    private Long clientId;
    private String number;
    private String fullname;
    private String nickname;
    private AccountType type;

    public CreateAccountRequest(Long clientId, String number, String fullname, String nickname, AccountType type) {
        this.clientId = clientId;
        this.number = number;
        this.fullname = fullname;
        this.nickname = nickname;
        this.type = type;
    }

    public CreateAccountRequest() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
