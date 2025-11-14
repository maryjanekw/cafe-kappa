package com.pluralsight.utillities;

public class Loading {

    // Loading method
    public static void kappaLoader() throws InterruptedException {
        System.out.print(Colors.KAPPA_GOLD + "Loading\n" + Colors.RESET);
        System.out.print(Colors.KAPPA_GREEN + "(｡•ᴗ•｡) " + Colors.RESET);
        for (int i = 0; i < 3; i++) {
            Thread.sleep(800);
            System.out.print(Colors.KAPPA_BLUE + ". " + Colors.RESET);
        }
        System.out.println();
    }
}
