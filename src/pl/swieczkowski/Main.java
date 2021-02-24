package pl.swieczkowski;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static char[][] board = new char[3][3];


    public static void main(String[] args) {

        String emptyBoard = "         ";
        fillBoard(emptyBoard);
        printBoard();
        controlLoop();

    }


    private static void fillBoard(String input) {
        int index = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = input.charAt(index++);
            }
        }
    }


    private static void printBoard() {
        System.out.println("---------");
        for (char[] chars : board) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(j == 0 ? "| " + chars[j] + " " : j == board.length - 1 ? chars[j] + " |" : chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }


    private static void getCoordinates(char c) {
        int x;
        int y;
        boolean isMarked = true;
        while (isMarked) {
            while (true) {
                System.out.println("Enter the coordinates:");
                try {
                    x = scanner.nextInt();
                    y = scanner.nextInt();


                    while (x < 1 || x > 3 || y < 1 || y > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        System.out.println("Enter the coordinates:");
                        x = scanner.nextInt();
                        y = scanner.nextInt();
                    }

                    if (board[x - 1][y - 1] == '_' || board[x - 1][y - 1] == ' ') {
                        board[x - 1][y - 1] = c;
                        isMarked = false;
                        printBoard();
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }


                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                }
            }
        }
    }

    private static void controlLoop() {

        int turn = 1;
        int markDeterminant;
        char currentMark = 'X';

        while (true) {
            if (hasBoardEmptySpace()) {
                markDeterminant = turn % 2;
                switch (markDeterminant) {
                    case 0:
                        currentMark = 'O';
                        getCoordinates(currentMark);
                        break;
                    case 1:
                        currentMark = 'X';
                        getCoordinates(currentMark);
                        break;
                }
                turn++;
                if (hasWinner(currentMark)) {
                    System.out.println(currentMark + " wins");
                    break;
                }

            } else {
                System.out.println("Draw");
                break;
            }
        }
    }


    private static boolean hasWinner(char c) {

        int winningRow;
        int winningColumn;
        int winningDiagonal;

        for (char[] chars : board) {
            winningRow = 0;
            for (char aChar : chars) {
                if (c == aChar) {
                    winningRow++;
                }
            }
            if (winningRow == 3) {
                return true;
            }
        }

        for (int i = 0; i < board.length; i++) {
            winningColumn = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (c == board[j][i]) {
                    winningColumn++;
                }
            }
            if (winningColumn == 3) {
                return true;
            }
        }

        winningDiagonal = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = i; j < i + 1; j++) {
                if (c == board[i][j]) {
                    winningDiagonal++;
                }
                if (winningDiagonal == 3) {
                    return true;
                }
            }
        }

        winningDiagonal = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 1 - i; j > board[i].length - 2 - i; j--) {
                if (c == board[i][j]) {
                    winningDiagonal++;
                }
                if (winningDiagonal == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasBoardEmptySpace() {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == '_' || aChar == ' ') {
                    return true;
                }
            }
        }
        return false;
    }
}






