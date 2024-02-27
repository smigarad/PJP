package org.example;

import java.io.InputStream;

public class Scanner {
    private String fileAsString;
    private int position;
    private boolean isComment = false;
    public Scanner(InputStream file){
        try{
            this.fileAsString = new String(file.readAllBytes());
        }
        catch(Exception e){
            e.printStackTrace();

    }
    }

    public Token nextToken(){
        //end of file
        if(position >= fileAsString.length() -1){
//            System.out.println("EOF");
                return new Token(TokenType.EOF, null);
          }
         char currentChar = fileAsString.charAt(position);
         if (currentChar == ' '){
             position++;
             return nextToken();
         }
         //operators
         if (currentChar == '(' || currentChar == ')' || currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == ';'
         || currentChar == '/') {
             position++;
             if(currentChar == '('){
                 return new Token(TokenType.LPAR, null);
             }
                if(currentChar == ')'){
                    return new Token(TokenType.RPAR, null);
                }
             if(currentChar == ';'){
                 return new Token(TokenType.SEMICOLON, null);
             }
             if(currentChar == '+'){
                    return new Token(TokenType.OP, "+");
                }
             if(currentChar == '-'){
                    return new Token(TokenType.OP, "-");
                }
                if(currentChar == '/') {
//                        return new Token(TokenType.OP, "/");
                    if (position - 1 < fileAsString.length() - 1 && fileAsString.charAt(position) == '/') {
                        position++;
                        isComment = true;
                        return nextToken();
                    }
                }
             return new Token(TokenType.OP, "*");
         }
        if (Character.isLetter(currentChar)){
            String value = "";
            if(isComment){
                while(currentChar != '\n' && position < fileAsString.length()){
                    position++;
                    currentChar = fileAsString.charAt(position);
                }
                isComment = false;
                if (position < fileAsString.length() - 1 && fileAsString.charAt(position) == '\n'){
                    position++;
                }
                return nextToken();
            }
//            if (currentChar == '\n'){
//                position++;
//                return nextToken();
//            }
            while(Character.isLetterOrDigit(currentChar) && position < fileAsString.length()){
                value += currentChar;
                position++;
                currentChar = fileAsString.charAt(position);
            }
            if (value.equals("div")){
                return new Token(TokenType.DIV, null);
            }
            if (value.equals("mod")){
                return new Token(TokenType.MOD, null);
            }
            return new Token(TokenType.ID, value);
        }
        if (Character.isDigit(currentChar)){
            String value = "";
            while(Character.isDigit(currentChar) && position < fileAsString.length()){
                value += currentChar;
                position++;
                currentChar = fileAsString.charAt(position);
            }
            return new Token(TokenType.NUM, value);
        }
        throw new RuntimeException("Invalid character: " + currentChar);
    }

}
