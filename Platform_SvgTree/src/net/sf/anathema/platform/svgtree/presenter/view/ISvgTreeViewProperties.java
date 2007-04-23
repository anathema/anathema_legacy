package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Cursor;

public interface ISvgTreeViewProperties {

  public String getNodeName(String nodeId);

  public boolean isRootNode(String nodeId);

  public String getToolTip(String nodeId);

  public Cursor getCursor(String nodeId);

  public Cursor getDefaultCursor();

  public Cursor getDragCursor();

  public Cursor getForbiddenCursor();

  public Cursor getZoomCursor();
}