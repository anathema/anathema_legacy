package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Color;

import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public interface ISvgTreeView extends IView, IDisposable {

  public void addNodeSelectionListener(INodeSelectionListener listener);

  public void setNodeBackgroundColor(String nodeId, Color color);

  public void setNodeAlpha(String nodeId, int alpha);

  public void addDocumentLoadedListener(IDocumentLoadedListener listener);

  public void setCanvasBackground(Color color);

  public void loadCascade(Document document, boolean resetView) throws DocumentException;

  public ISpecialNodeViewManager getSpecialViewManager();
}