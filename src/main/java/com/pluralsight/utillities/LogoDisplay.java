package com.pluralsight.utillities;

public class LogoDisplay {

    public static void showBanner() {
        System.out.println(Colors.KAPPA_GREEN + """
             ____       __   __   _  __                       
            / ___|__ _ / _| /_/  | |/ /__ _ _ __  _ __   __ _ 
           | |   / _` | |_ / _ \\ | ' // _` | '_ \\| '_ \\ / _` |
           | |__| (_| |  _|  __/ | . \\ (_| | |_) | |_) | (_| |
            \\____\\__,_|_|  \\___| |_|\\_\\__,_| .__/| .__/ \\__,_|
                                            |_|   |_|          
            """ + Colors.RESET);
    }

    public static void showMascot() {
        System.out.println("""
                  (_)\s
                 /⌐v¬\\
                 (^°^)
                ()≡ ()
                  └┴┘\s
                """);
    }

}
