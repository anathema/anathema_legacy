package net.sf.anathema.campaign.view;

import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.campaign.note.view.IBasicItemView;
import net.sf.anathema.framework.view.item.AbstractItemView;

import javax.swing.Icon;
import javax.swing.JComponent;

public class BasicItemView extends AbstractItemView implements IBasicItemView {
  private BasicItemDescriptionView descriptionView = new BasicItemDescriptionView();

  public BasicItemView(String name, Icon icon) {
    super(name, icon);
  }

  @Override
  public IBasicItemDescriptionView getDescriptionView() {
    return descriptionView;
  }

  @Override
  public JComponent getComponent() {
    return descriptionView.getComponent();
  }
}