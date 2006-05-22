package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.framework.magic.view.ISpecialCharmView;
import net.sf.anathema.charmtree.batik.IBoundsCalculator;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.w3c.dom.Element;

public interface ISVGSpecialCharmView extends ISpecialCharmView {
  public Element initGui(SVGOMDocument document, IBoundsCalculator boundsCalculator);

  public void setVisible(boolean visible);

  public void reset();
}