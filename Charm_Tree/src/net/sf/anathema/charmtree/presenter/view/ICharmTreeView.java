package net.sf.anathema.charmtree.presenter.view;

import java.awt.Color;

import javax.swing.JComponent;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;

import org.w3c.dom.svg.SVGDocument;

public interface ICharmTreeView {
  public JComponent getComponent();

  public IAnathemaCanvas getCanvas();

  public void addCharmSelectionListener(ICharmSelectionListener listener);

  public void setCharmBackgroundColor(String charmId, Color color);

  public void setCharmAlpha(String charmId, int alpha);

  public void addDocumentLoadedListener(IDocumentLoadedListener listener);

  public void setCanvasBackground(Color color);

  public void dispose();

  public void setProperties(ICharmTreeViewProperties viewProperties);

  public void loadCascade(SVGDocument document);

  public IBoundsCalculator getBoundsCalculator();
}