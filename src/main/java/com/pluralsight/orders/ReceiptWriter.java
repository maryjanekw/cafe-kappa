package com.pluralsight.orders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    private static final DateTimeFormatter FIlE_TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMddd-HHmmss");
    private static final Path RECEIPTS_DIRECTORY = Paths.get("receipts");

    public static Path writeReceipt(Order order) throws IOException{
        if (order == null){
            throw new IllegalArgumentException("Order cannot be completed");
        }

        // Current time for the receipt
        LocalDateTime time = LocalDateTime.now();

        // Get the receipt text from order class
        String text = order.buildReceiptText();

        // Delegate to the helper method
        return writeReceipt(text, time);
    }

    // Helper method
    public static Path writeReceipt(String receiptText, LocalDateTime timestamp) throws IOException{
        if (receiptText == null || receiptText.isBlank()){
            throw new IllegalArgumentException("Receipt cannot be completed");
        }
        // Ensures the receipt directory exists
        Files.createDirectories(RECEIPTS_DIRECTORY);

        // Final file name
        String fileName = timestamp.format(FIlE_TIMESTAMP) + ".txt";
        Path finalPath = RECEIPTS_DIRECTORY.resolve(fileName);

        // Temp file to help with safer write
        Path tempPath = RECEIPTS_DIRECTORY.resolve(fileName + ".temp");

        try {
            // 1. writes receipt to temp file
            Files.writeString(tempPath, receiptText, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // 2. move temp file to final file automatically
            Files.move(tempPath, finalPath, StandardCopyOption.ATOMIC_MOVE);

            return finalPath;
        }catch (IOException ex){
            Files.deleteIfExists(tempPath);  // clear temp file for next order
            throw ex;
        }
    }


}
