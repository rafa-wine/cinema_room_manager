package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] cinemaRoom = new char[rows][seats];
        double[] statisticsVar = new double[4]; 
        
        statisticsVar[3] = rows * seats <= 60 ? rows * seats * 10 : (((int) (rows / 2)) * seats * 10) + ((((int) (rows / 2) + 1) * seats * 8));

        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                cinemaRoom[i][j] = 'S';
            }
        }

        boolean exit = false;

        do {
            System.out.print("""
                             
                            1. Show the seats
                            2. Buy a ticket
                            3. Statistics
                            0. Exit
                             
                            """);

            int selection = scanner.nextInt();
            
            switch (selection) {
                case 1 :
                    showSeats(cinemaRoom);
                    break;
                case 2 :
                    buyTicket(rows, seats, cinemaRoom, statisticsVar);
                    break;
                case 3 :
                    statistics(statisticsVar);
                    break;
                case 0 :
                    exit = true;
                    break;
            }
        } while (!exit);
        
    } 

    public static void showSeats(char[][] cinemaRoom) {
        boolean headText = true;
            
        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                if (headText) {
                    System.out.print(j == 0 ? "Cinema:\n" + "  " + (j + 1) : " " + (j + 1));
                } else {
                    System.out.print(j == 0 ? "\n" + (i + 1) + " " + cinemaRoom[i][j] : " " + cinemaRoom[i][j]);
                }
            }

            if (i == 0 && headText) {
                i = -1;
                headText = false;
            }
        }

         System.out.println();
    }

    public static void buyTicket(int rows, int seats, char[][] cinemaRoom, double[] statisticsVar) {
        Scanner scanner = new Scanner(System.in);
        
        do{
            System.out.println("\nEnter a row number:");
            int rowNum = scanner.nextInt();
    
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();
    
            if (rowNum - 1 > cinemaRoom.length - 1 || seatNum - 1 > cinemaRoom[0].length - 1) {
                System.out.println("\nWrong input!\n");
            } else {
                if (cinemaRoom[rowNum - 1][seatNum - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    int price = rows * seats <= 60 ? 10 : rowNum <= ((int) (rows / 2)) ? 10 : 8;
                    System.out.printf("%nTicket price: $%d%n", price);
                    cinemaRoom[rowNum - 1][seatNum - 1] = 'B';
                    statisticsVar[0] += 1;
                    statisticsVar[1] += (double) 100 / (double) (cinemaRoom.length * cinemaRoom[0].length);
                    statisticsVar[2] += price;
                    break;
                }
            }
        } while (true);    
    }

    public static void statistics(double[] statisticsVar) {
        System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f%%%nCurrent income: $%d%nTotal income: $%d%n", (int) statisticsVar[0], statisticsVar[1], (int) statisticsVar[2], (int) statisticsVar[3]);
    }
    
}