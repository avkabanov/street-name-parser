package com.kabanov.street_parser.io.reader;

import java.util.Scanner;

public class ConsoleReader implements InputReader {
    
    @Override
    public String readLine() {
        System.out.println("Input address string: ");
        try (Scanner in = new Scanner(System.in)) {
            return in.nextLine();
        }
    }
}
