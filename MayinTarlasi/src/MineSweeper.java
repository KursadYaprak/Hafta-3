import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class MineSweeper {

    int lineNumber, pillarNumber, size;
    int[][] map;
    int[][] board;
    boolean[][] openBoard;

    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    boolean isGame = true;

    MineSweeper(int lineNumber, int pillarNumber) {
        this.lineNumber = lineNumber;
        this.pillarNumber = pillarNumber;
        this.size = lineNumber * pillarNumber;
        this.map = new int[lineNumber][pillarNumber];
        this.board = new int[lineNumber][pillarNumber];
        this.openBoard = new boolean[lineNumber][pillarNumber];
    }

    public void startGame() {

        int lineChoice, pillarChoice, rightChoice = 0;

        setMines();
        createBoard(map);

        System.out.println("Oyununuz Başladı.");

        while(isGame) {

        createBoard(board);

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
        if (!openBoard[lineChoice][pillarChoice]) {
            if (map[lineChoice][pillarChoice] != -1) {
                checkMine(lineChoice, pillarChoice);
                rightChoice++;
                if (rightChoice == (size - (size / 4))) {
                    System.out.println("Tebrikler. Tüm Mayınsız Alanları Buldunuz.");
                    createBoard(board);
                    break;
                }
            } else {
                System.out.println("Mayına Bastınız !\nOyun Bitti.");
                createBoard(map);
                isGame = false;
            }
            openBoard[lineChoice][pillarChoice] = true;
        } else {
            System.out.println("Daha Önce Bu Seçimi Yaptınız.");
            continue;
        }
    }
}

    public void setMines() {

        int randLine, randPillar, mine = 0;

        while (mine != (size / 4)) {
            randLine = rand.nextInt(lineNumber);
            randPillar = rand.nextInt(pillarNumber);
            if (map[randLine][randPillar] != -1) {
                map[randLine][randPillar] = -1;
                mine++;
            }
        }
    }

    public void createBoard(int[][] arr) {

        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j < pillarNumber; j++) {
                if (arr[i][j] >= 0)
                    System.out.print(" ");
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void checkMine(int i, int j) {
        if (map[i][j] == 0) {
            if((i > 0) && (j > 0) && (map[i-1][j-1] == -1)) {
                board[i][j]++;
            }
            if((i > 0) && (map[i-1][j] == -1)) {
                board[i][j]++;
            }
            if((j < pillarNumber - 1) && (i > 0) && (map[i-1][j+1] == -1)) {
                board[i][j]++;
            }
            if((j > 0) && (map[i][j-1] == -1)) {
                board[i][j]++;
            }
            if((j < pillarNumber - 1) && (map[i][j+1] == -1)) {
                board[i][j]++;
            }
            if((i < lineNumber - 1) && (j > 0) && (map[i+1][j-1] == -1)) {
                board[i][j]++;
            }
            if((i < lineNumber - 1) && (map[i+1][j] == -1)) {
                board[i][j]++;
            }
            if((j < pillarNumber - 1) && (i < lineNumber - 1) && (map[i+1][j+1] == -1)) {
                board[i][j]++;
            }
        }
    }


}