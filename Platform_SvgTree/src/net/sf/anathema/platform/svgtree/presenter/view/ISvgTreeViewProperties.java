package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Cursor;

public interface ISvgTreeViewProperties {

  String getNodeName(String nodeId);

  boolean isRootNode(String nodeId);

  String getToolTip(String nodeId);

  Cursor getCursor(String nodeId);

  Cursor getDefaultCursor();

  Cursor getDragCursor();

  Cursor getForbiddenCursor();

  Cursor getZoomCursor();

  Cursor getControlCursor();
}