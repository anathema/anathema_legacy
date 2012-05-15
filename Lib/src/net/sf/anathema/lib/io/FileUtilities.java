package net.sf.anathema.lib.io;

import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

import java.io.File;
import java.io.IOException;

public class FileUtilities {

  protected FileUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static void deleteFileOrDirectory(final File file) throws IOException {
    if (!file.exists()) {
      return;
    }
    if (file.isDirectory()) {
      final File[] files = file.listFiles();
      for (final File file2 : files) {
        deleteFileOrDirectory(file2);
      }
    }
    if (!file.delete()) {
      throw new IOException("delete failed for file '" + file.getAbsolutePath() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }
}