package net.sf.anathema.character.lunar.beastform.view;

import javax.swing.JPanel;

import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class SecondEditionBeastformOverviewView implements IBeastformOverviewView {
  private final OverviewCategory category;
  private ILabelledAlotmentView attributeView;
  private ILabelledAlotmentView giftView;
  private final JPanel content = new JPanel();

  public SecondEditionBeastformOverviewView(JPanel parent, IBeastformOverviewViewProperties properties) {
    this.category = new OverviewCategory(content, properties.getOverviewBorderString(), false);
    
    giftView = category.addAlotmentView(properties.getGiftPicksString(), 2);
    parent.add(content);
  }

  public ILabelledAlotmentView getAttributeOverview() {
    return attributeView;
  }

  public ILabelledAlotmentView getGiftOverview() {
    return giftView;
  }
}