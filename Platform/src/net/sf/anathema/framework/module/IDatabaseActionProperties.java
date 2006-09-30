package net.sf.anathema.framework.module;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;

import net.sf.anathema.framework.itemdata.model.IItemData;

public interface IDatabaseActionProperties {

  public String getItemTypeId();

  public String getActionName();

  public Icon getActionIcon();

  public String getToolTipText();

  public String getProgressMonitorTitle();

  public String getProgressTaskTitle();

  public String getItemId();

  public IItemData createItemData(File repositoryFolder) throws IOException;
}