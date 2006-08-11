package net.sf.anathema;

import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.java.plugin.boot.Boot;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    showSplashScreen();
    Boot.main(arguments);
    SplashWindow.disposeSplash();
  }

  private static void showSplashScreen() throws IOException, InterruptedException {
    BufferedInputStream inputStream = new BufferedInputStream(
        AnathemaBootLoader.class.getResourceAsStream("/icons/core/AnathemaSplash3.png")); //$NON-NLS-1$
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int numChars;
    while ((numChars = inputStream.read(buffer)) > 0) {
      outputStream.write(buffer, 0, numChars);
    }
    SplashWindow.splash(Toolkit.getDefaultToolkit().createImage(outputStream.toByteArray()));
  }
}