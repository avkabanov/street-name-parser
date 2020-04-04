package com.kabanov.street_parser.io.reader;

import java.util.Scanner;

public class ConsoleInputStreamReader implements InputReader {
    
    @Override
    public String readLine() {
        try (Scanner in = new Scanner(System.in)) {
            return in.nextLine();
        }
    }
}
