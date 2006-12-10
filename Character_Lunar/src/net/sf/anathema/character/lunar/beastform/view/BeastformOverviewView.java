package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JComponent;

import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class BeastformOverviewView implements IBeastformOverviewView, IView {
  private final OverviewCategory category;
  private ILabelledAlotmentView attributeView;
  private ILabelledAlotmentView giftView;
  private JComponent content;
  private final IBeastformOverviewViewProperties properties;

  public BeastformOverviewView(IBeastformOverviewViewProperties properties) {
    this.properties = properties;
    this.category = new OverviewCategory(properties.getOverviewBorderString(), false);
  }

  public JComponent getComponent() {
    if (content == null) {
      attributeView = category.addAlotmentView(properties.getAttributeDotsString(), 2);
      giftView = category.addAlotmentView(properties.getGiftPicksString(), 2);
      content = category.getComponent();
    }
    return content;
  }

  public ILabelledAlotmentView getAttributeOverview() {
    return attributeView;
  }

  public ILabelledAlotmentView getGiftOverview() {
    return giftView;
  }
}