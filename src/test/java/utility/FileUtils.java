package utility;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
public class FileUtils {

    // Method to copy a file from source to destination
    public static void copyFile(File source, File destination) throws IOException {
        // Check if the source file exists
        if (source.exists()) {
            // Create parent directories if they do not exist
            destination.getParentFile().mkdirs();
            // Copy the file to the destination
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully to " + destination.getPath());
        } else {
            throw new IOException("Source file does not exist: " + source.getPath());
        }
    }
}
