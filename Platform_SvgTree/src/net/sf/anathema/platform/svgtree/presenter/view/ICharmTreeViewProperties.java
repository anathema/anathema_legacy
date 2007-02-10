package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Cursor;
import java.awt.Dimension;

public interface ICharmTreeViewProperties {
  public static final String REQUIREMENT = "Requirement"; //$NON-NLS-1$

  public String getNodeName(String charmId);

  public boolean isRootCharm(String charmId);

  public boolean isCharmSelected(String charmId);

  public Cursor getAddCursor();

  public Cursor getRemoveCursor();

  public Cursor getDefaultCursor();

  public boolean isCharmLearnable(String charmId);

  public boolean isCharmUnlearnable(String charmId);

  public String getToolTip(String charmId);

  public Dimension getDimension();

  public void setDimension(Dimension dimension);

  public boolean isRequirementNode(String nodeId);
}