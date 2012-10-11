package net.sf.anathema.campaign.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.campaign.note.view.IBasicItemDescriptionView;
import net.sf.anathema.campaign.note.view.IBasicItemView;
import net.sf.anathema.framework.view.item.AbstractItemView;

import javax.swing.Icon;
import javax.swing.JComponent;

public class BasicItemView extends AbstractItemView implements IBasicItemView {
  private BasicItemDescriptionView descriptionView;

  public BasicItemView(String name, Icon icon) {
    super(name, icon);
  }

  @Override
  public IBasicItemDescriptionView addDescriptionView() {
    Preconditions.checkArgument(descriptionView == null, "Only one description view allowed."); //$NON-NLS-1$
    this.descriptionView = new BasicItemDescriptionView();
    return descriptionView;
  }

  @Override
  public JComponent getComponent() {
    return descriptionView.getComponent();
  }
}