package com.vytrack.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * A class containing static convenience methods for screenshot creation and manipulation.
 * Screenshot is achieved via WebDriver
 * author: Serkan Demirel
 */
public class AnnotatedImage {

/**
 * @param driver - webDriver that runs Chrome
 * @param className - subfolder that is created under main image folder
 * @param message - text that goes on the image. If message contains "PASSED:", gray frame
 * @param textPosition - top, middle, bottom
 * @param fontSize - fonts-ze provided in float
 * @return absolute path to the created image file.
 * @throws IOException
 */
public static String getAnnotatedScreenShot(WebDriver driver, String className, String message, String textPosition, float fontSize) throws IOException {
   Properties prop = new Properties();
   prop.load(new FileInputStream("credentials.properties"));
   String pathFromContentRoot = prop.getProperty("pathFromContentRoot");
   String fileLocation = pathFromContentRoot + "/" + className + "/" + LocalDate.now() + "/";
   String fileName = LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".png";
   
   BufferedImage image = takeScreenShot(driver);
   int y = drawRectangle(image, message, textPosition, fontSize);
   drawText(image, message, fontSize, y);
   return saveImage(image, fileLocation, fileName);
}

private static BufferedImage takeScreenShot(WebDriver driver) throws IOException {
   TakesScreenshot scrShot = (TakesScreenshot) driver;
   return ImageIO.read(scrShot.getScreenshotAs(OutputType.FILE));
}

private static int getYPosition(int imageHeight, String textPosition) {
   switch (textPosition) {
      case "top":
         return (int) (imageHeight * .10);
      case "middle":
         return imageHeight / 2;
      case "bottom":
         return (int) (imageHeight * .80);
      default:
         return 0;
   }
}

private static int drawRectangle(BufferedImage image, String message, String textPosition, float fontSize) throws IOException {
   int y = getYPosition(image.getHeight(), textPosition);
   Graphics2D g2d = image.createGraphics();
   g2d.setColor(message.contains("FAILED:") ? Color.RED : Color.DARK_GRAY);
   g2d.fillRect(20, y, Math.min(image.getWidth() - 20, message.length() * (int) (fontSize / 1.5)), (int) (fontSize * 1.2));
   g2d.dispose();
   return y + (int) fontSize;
}

private static void drawText(BufferedImage image, String message, float fontSize, int y) {
   Graphics g = image.getGraphics();
   g.setFont(g.getFont().deriveFont(fontSize));
   g.setColor(Color.WHITE);
   g.drawString(message, 40, y);
   g.dispose();
}

private static String saveImage(BufferedImage image, String path, String fileName) throws IOException {
   if (!new File(path).exists()) new File(path).mkdirs();
   File imageFile = new File(path + fileName);
   ImageIO.write(image, "png", imageFile);
   return imageFile.getAbsolutePath();
}
}
