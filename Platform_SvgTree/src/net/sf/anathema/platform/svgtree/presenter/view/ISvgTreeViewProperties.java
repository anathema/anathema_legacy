package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Cursor;
import java.awt.Dimension;

public interface ISvgTreeViewProperties {
  public static final String REQUIREMENT = "Requirement"; //$NON-NLS-1$

  public String getNodeName(String nodeId);

  public boolean isRootNode(String nodeId);

  public boolean isNodeSelected(String nodeId);

  public Cursor getAddCursor();

  public Cursor getRemoveCursor();

  public Cursor getDefaultCursor();

  public boolean isNodeSelectable(String nodeId);

  public boolean isNodeDeselectable(String nodeId);

  public String getToolTip(String nodeId);

  public Dimension getDimension();

  public void setDimension(Dimension dimension);

  // TODO Löschen?
  @Deprecated
  public boolean isRequirementNode(String nodeId);
}