package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try(InputStream file = new FileInputStream(
                new File("src/main/resources/input.txt")
        )){
            var scanner = new Scanner(file);
            var token = scanner.nextToken();
            while(token.getType() != TokenType.EOF){
                String value = token.getValue() == null ? "" : token.getValue();
                System.out.println(token.getType() + " " + value);
                token = scanner.nextToken();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}