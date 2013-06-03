package net.sf.anathema.campaign.item;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClosedFileCollector {

  private PlotItemManagement itemManagement;
  private IPrintNameFileAccess printNameFileAccess;

  public ClosedFileCollector(PlotItemManagement managementModel, IPrintNameFileAccess printNameFileAccess) {
    this.itemManagement = managementModel;
    this.printNameFileAccess = printNameFileAccess;
  }

  public Collection<PrintNameFile> collectClosedPrintNameFiles(IItemType type) {
    List<PrintNameFile> closedFiles = new ArrayList<>();
    for (PrintNameFile file : printNameFileAccess.collectAllPrintNameFiles(type)) {
      if (!itemManagement.isOpen(file.getRepositoryId(), type)) {
        closedFiles.add(file);
      }
    }
    return closedFiles;
  }

  public boolean containsClosed(IItemType... types) {
    boolean hasClosed = false;
    for (IItemType type : types) {
      hasClosed |= !collectClosedPrintNameFiles(type).isEmpty();
    }
    return hasClosed;
  }
}
