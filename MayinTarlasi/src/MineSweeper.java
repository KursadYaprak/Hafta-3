import java.util.Scanner;
import java.util.Random;

public class MineSweeper {

    int lineNumber, pillarNumber, size;
    char[][] map;
    char[][] board;

    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    boolean isGame = true;

    MineSweeper(int lineNumber, int pillarNumber) {
        this.lineNumber = lineNumber;
        this.pillarNumber = pillarNumber;
        this.size = lineNumber * pillarNumber;
        this.map = new char[lineNumber][pillarNumber];
        this.board = new char[lineNumber][pillarNumber];
    }

    public void startGame() {

        int lineChoice, pillarChoice, rightChoice = 0;

        setMines();
        printBoard(map);

        System.out.println("Oyununun Başladı !");

        while(isGame) {

        printBoard(map);

        System.out.print("Bir Satır Seçiniz : ");
        lineChoice = input.nextInt();
        System.out.print("Bir Sütun Seçiniz : ");
        pillarChoice = input.nextInt();

        if (lineChoice < 0 || lineChoice > lineNumber - 1) {
            System.out.println("Oyun Alanına Ait Olmayan Bir Konum Girdiniz.");
            continue;
        }
        if (pillarChoice < 0 || pillarChoice > pillarNumber - 1) {
            System.out.println("Oyun Alanına Ait Olmayan Bir Konum Girdiniz.");
            continue;
        }
        if (board[lineChoice][pillarChoice] != '*') {
            setMines();
            rightChoice++;
            if (rightChoice == (size - (size / 4))) {
                System.out.println("Tebrikler. Tüm Mayınsız Alanları Buldunuz.");
                break;
            }
        } else {
            System.out.println("Mayına Bastınız !\nOyun Bitti.");
            isGame = false;
        }
    }
}

    public void setMines() {

        int randLine, randPillar, mine = 0;

        while (mine != (size / 4)) {
            randLine = rand.nextInt(lineNumber);
            randPillar = rand.nextInt(pillarNumber);
            if (map[randLine][randPillar] != '*') {
                map[randLine][randPillar] = '*';
                mine++;
            }
        }
    }

    public void createBoard() {

        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j < pillarNumber; j++) {
                map[i][j] = '-';
            }
        }
    }

    public void printBoard(char[][] arr) {

        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j < pillarNumber; j++) {
                System.out.print(arr[i][j] = ' ');
            }
            System.out.println();
        }
    }

    /*public void checkMine(int i, int j) {
        int count = 0;
        if (gameMap[i][j] == '-') {
            if((i > 0) && (j > 0) && (gameMap[i-1][j-1] == '*')) {
                count++;
            }
            if((i > 0) && (gameMap[i-1][j] == '*')) {
                count++;
            }
            if((j < pillar - 1) && (i > 0) && (gameMap[i-1][j+1] == '*')) {
                count++;
            }
            if((j > 0) && (gameMap[i][j-1] == '*')) {
                count++;
            }
            if((j < pillar - 1) && (gameMap[i][j+1] == '*')) {
                count++;
            }
            if((i < line - 1) && (j > 0) && (gameMap[i+1][j-1] == '*')) {
                count++;
            }
            if((i < line - 1) && (gameMap[i+1][j] == '*')) {
                count++;
            }
            if((j < pillar - 1) && (i < line - 1) && (gameBoard[i+1][j+1] == '*')) {
                count++;
            }
            gameMap[i][j] = (char) count;
        }
    }*/


}