package net.sf.anathema.campaign.music.impl.model.tracks;

import java.io.File;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.Ensure;

public class FileUtilities {

  private FileUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static int getFileCount(File folder, boolean recursive, IPredicate<File> predicate) {
    Ensure.ensureArgumentNotNull(folder);
    Ensure.ensureTrue("Must be an existing folder.", folder.exists() && folder.isDirectory()); //$NON-NLS-1$
    int count = 0;
    for (File file : folder.listFiles()) {
      if (predicate.evaluate(file)) {
        count++;
      }
      if (file.isDirectory() && recursive) {
        count += getFileCount(file, recursive, predicate);
      }
    }
    return count;
  }
}