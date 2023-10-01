import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int line, pillar;

        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !");
        System.out.print("Tahtanın Satır Sayısını Giriniz : ");
        line = input.nextInt();
        System.out.print("Tahtanın Sütun Sayısını Giriniz : ");
        pillar = input.nextInt();

        MineSweeper mine = new MineSweeper(line, pillar);
        mine.startGame();
    }
}