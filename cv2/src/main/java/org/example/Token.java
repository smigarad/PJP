package org.example;

public class Token {
    private TokenType type;
    private String value;
    public TokenType getType() {
        return type;
    }
    public String getValue() {
        return value;
    }
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
}
