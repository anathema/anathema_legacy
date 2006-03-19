package net.sf.anathema.campaign.model;

import net.sf.anathema.framework.repository.tree.IRepositoryTreeModel;
import net.sf.anathema.framework.view.PrintNameFile;

public interface ISeriesContentModel extends IRepositoryTreeModel {

  public void addItem(PrintNameFile item);

  public void removeItem(PrintNameFile item);
}