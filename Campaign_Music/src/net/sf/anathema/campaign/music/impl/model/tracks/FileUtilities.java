package net.sf.anathema.campaign.music.impl.model.tracks;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import java.io.File;

public class FileUtilities {

  public static int getFileCount(File folder, Predicate<File> predicate) {
    Preconditions.checkArgument(folder.isDirectory(), "Must be an existing folder."); //$NON-NLS-1$
    int count = 0;
    for (File file : folder.listFiles()) {
      if (predicate.apply(file)) {
        count++;
      }
      if (file.isDirectory()) {
        count += getFileCount(file, predicate);
      }
    }
    return count;
  }
}