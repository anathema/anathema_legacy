package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Color;

import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.w3c.dom.svg.SVGDocument;

public interface ISvgTreeView extends IView, IDisposable {

  public IAnathemaCanvas getCanvas();

  public void addNodeSelectionListener(INodeSelectionListener listener);

  public void setNodeBackgroundColor(String nodeId, Color color);

  public void setNodeAlpha(String nodeId, int alpha);

  public void addDocumentLoadedListener(IDocumentLoadedListener listener);

  public void setCanvasBackground(Color color);

  public void setProperties(ISvgTreeViewProperties viewProperties);

  public void loadCascade(SVGDocument document);

  public IBoundsCalculator getBoundsCalculator();
}