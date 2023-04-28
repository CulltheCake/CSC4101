package rdp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexer {
    public static void Tokenize(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            int i = 0;
            while (i < line.length()) {
                char c = line.charAt(i);
                // Skip whitespace
                if (Character.isWhitespace(c)) {
                    i++;
                    continue;
                }
                // Check for comments
                if (c == '/') {
                    i++;
                    if (i < line.length() && line.charAt(i) == '/') {
                        // Skip to end of line
                        break;
                    } else {
                        System.out.println("INVALID TOKEN: /");
                        return;
                    }
                }
                // Check for reserved symbols
                switch (c) {
                    case '=':
                        System.out.println("ASSIGN");
                        break;
                    case '+':
                        System.out.println("ADD_OP");
                        break;
                    case '(':
                        System.out.println("LP");
                        break;
                    case ')':
                        System.out.println("RP");
                        break;
                    case '*':
                        System.out.println("MUL_OP");
                        break;
                    case '{':
                        System.out.println("LB");
                        break;
                    case '}':
                        System.out.println("RB");
                        break;
                    case '/':
                        System.out.println("DIV_OP");
                        break;
                    case '%':
                        System.out.println("MOD_OP");
                        break;
                    case '|':
                        System.out.println("OR");
                        break;
                    case '&':
                        System.out.println("AND");
                        break;
                    case '>':
                        if (i + 1 < line.length() && line.charAt(i + 1) == '=') {
                            System.out.println("GE");
                            i++;
                        } else {
                            System.out.println("GT");
                        }
                        break;
                    case '<':
                        if (i + 1 < line.length() && line.charAt(i + 1) == '=') {
                            System.out.println("LE");
                            i++;
                        } else {
                            System.out.println("LT");
                        }
                        break;
                    case '!':
                        if (i + 1 < line.length() && line.charAt(i + 1) == '=') {
                            System.out.println("NE");
                            i++;
                        } else {
                            System.out.println("NEG");
                        }
                        break;
                    case ',':
                        System.out.println("COMMA");
                        break;
                    case ';':
                        System.out.println("SEMI");
                        break;
                    default:
                        // Check for identifiers or integer constants
                        if (Character.isLetter(c)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(c);
                            i++;
                            while (i < line.length() && (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_')) {
                                sb.append(line.charAt(i));
                                i++;
                            }
                            String identifier = sb.toString();
                            switch (identifier) {
                                case "if":
                                    System.out.println("IF");
                                    break;
                                case "for":
                                    System.out.println("FOR");
                                    break;
                                case "while":
                                    System.out.println("WHILE");
                                    break;
                                case "procedure":
                                    System.out.println("PROC");
                                    break;
                                case "return":
                                    System.out.println("RETURN");
                                    break;
                                case "int":
                                    System.out.println("INT");
                                    break;
                                case "else":
                                    System.out.println("ELSE");
                                    break;
                                case "do":
                                    System.out.println("DO");
                                    break;
                                case "break":
                                    System.out.println("BREAK");
                                    break;
                                case "end":
                                	System.out.println("END");
                                	break;
                                default:
                                	System.out.println("Error");
                                	break; 
                            }
                        }
                }
            }
        }
    }
                         // Method to check if a character is a digit
                            public static boolean isDigit(char c) {
                                return c >= '0' && c <= '9';
                            }

                            // Method to check if a character is an alphanumeric character
                            public static boolean isAlphaNumeric(char c) {
                                return isDigit(c) || isLetter(c);
                            }

                            // Method to tokenize the input file
                            public static void Tokenize(String fileName) {
                                try {
                                    // Open the file and create a buffered reader to read character by character
                                    FileReader fileReader = new FileReader(fileName);
                                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                                    // Variables to keep track of the current token being built and the current character being read
                                    StringBuilder currentToken = new StringBuilder();
                                    char currentChar;

                                    // Loop through each character in the file
                                    while ((currentChar = (char) bufferedReader.read()) != (char) -1) {
                                        // If the current character is a space, skip to the next character
                                        if (currentChar == ' ') {
                                            continue;
                                        }
                                        
                                        // If the current character is a newline or a tab, print the current token and skip to the next character
                                        if (currentChar == '\n' || currentChar == '\r' || currentChar == '\t') {
                                            printToken(currentToken.toString());
                                            currentToken = new StringBuilder();
                                            continue;
                                        }

                                        // If the current character is an operator or delimiter, print the current token and the operator/delimiter as separate tokens
                                        if (isOperator(currentChar) || isDelimiter(currentChar)) {
                                            printToken(currentToken.toString());
                                            printToken(String.valueOf(currentChar));
                                            currentToken = new StringBuilder();
                                            continue;
                                        }

                                        // If the current character is a letter, start building an identifier token
                                        if (isLetter(currentChar)) {
                                            currentToken.append(currentChar);
                                            while (isAlphaNumeric((char) bufferedReader.peek())) {
                                                currentToken.append((char) bufferedReader.read());
                                            }
                                            String tokenString = currentToken.toString();
                                            if (isReserved(tokenString)) {
                                                printToken(tokenString.toUpperCase());
                                            } else {
                                                printToken("IDENT");
                                            }
                                            currentToken = new StringBuilder();
                                            continue;
                                        }

                                        // If the current character is a digit, start building an integer token
                                        if (isDigit(currentChar)) {
                                            currentToken.append(currentChar);
                                            while (isDigit((char) bufferedReader.peek())) {
                                                currentToken.append((char) bufferedReader.read());
                                            }
                                            printToken("INT_CONST");
                                            currentToken = new StringBuilder();
                                            continue;
                                        }

                                        // If the current character is not recognized, throw an exception
                                        throw new Exception("Invalid character: " + currentChar);
                                    }

                                    // Close the buffered reader
                                    bufferedReader.close();
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }

                               
	public static void main(String[] args) throws IOException {
		Lexer.Tokenize("C:/Users/baogo/Downloads/testcase.txt");
	}
}
