/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtilities {

  public static void close(final Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      }
      catch (final IOException exception) {
        // Silent
      }
    }
  }

  public static void copyStream(final InputStream in, final OutputStream out) throws IOException {
    copyStream(in, out, 4096);
  }

  public static void copyStream(final InputStream in, final OutputStream out, final int bufferSize)
      throws IOException {
    final byte[] buffer = new byte[bufferSize];
    int numChars;
    while ((numChars = in.read(buffer)) > 0) {
      out.write(buffer, 0, numChars);
    }
  }

}