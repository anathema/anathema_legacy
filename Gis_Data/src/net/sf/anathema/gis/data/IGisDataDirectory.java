package net.sf.anathema.gis.data;

import java.io.File;

public interface IGisDataDirectory {

  public boolean canWrite();

  public boolean canRead();
  
  public File getDirectory();
}