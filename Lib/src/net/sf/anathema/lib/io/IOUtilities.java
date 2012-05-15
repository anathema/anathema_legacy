package net.sf.anathema.lib.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtilities {

  public static void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      }
      catch (IOException exception) {
        // Silent
      }
    }
  }

  public static void copyStream(InputStream in, OutputStream out) throws IOException {
    copyStream(in, out, 4096);
  }

  public static void copyStream(InputStream in, OutputStream out, int bufferSize)
      throws IOException {
    byte[] buffer = new byte[bufferSize];
    int numChars;
    while ((numChars = in.read(buffer)) > 0) {
      out.write(buffer, 0, numChars);
    }
  }

}