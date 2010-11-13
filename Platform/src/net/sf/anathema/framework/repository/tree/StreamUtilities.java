package net.sf.anathema.framework.repository.tree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtilities {

  public static void writeInputStreamToOutputStream(InputStream inStream, OutputStream outStream) throws IOException {
    byte buffer[] = new byte[512];
    int lengthRead = 0;
    while ((lengthRead = inStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, lengthRead);
    }
    inStream.close();
  }
}