package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.view.PrintNameFile;

import java.io.File;
import java.io.IOException;

public interface PrintNameFileReader {

  PrintNameFile readPrintName(File file, IItemType itemType) throws IOException;
}