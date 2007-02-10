package net.sf.anathema.platform.svgtree.presenter.view;

import java.awt.Color;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

import org.w3c.dom.svg.SVGDocument;

public interface ICharmTreeView extends IView, IDisposable {

  public IAnathemaCanvas getCanvas();

  public void addCharmSelectionListener(ICharmSelectionListener listener);

  public void setCharmBackgroundColor(String charmId, Color color);

  public void setCharmAlpha(String charmId, int alpha);

  public void addDocumentLoadedListener(IDocumentLoadedListener listener);

  public void setCanvasBackground(Color color);

  public void setProperties(ICharmTreeViewProperties viewProperties);

  public void loadCascade(SVGDocument document);

  public IBoundsCalculator getBoundsCalculator();
}