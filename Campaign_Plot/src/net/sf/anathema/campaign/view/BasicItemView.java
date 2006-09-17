package net.sf.anathema.campaign.view;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.itemdata.view.IBasicItemView;
import net.sf.anathema.framework.view.item.AbstractItemView;

public class BasicItemView extends AbstractItemView implements IBasicItemView {

  private BasicItemDescriptionView descriptionView;

  public BasicItemView(String name, Icon icon) {
    super(name, icon);
  }

  @Override
  public void dispose() {
    // Nothing to do
  }

  public IBasicItemDescriptionView addDescriptionView() {
    Ensure.ensureNull("Only one description view allowed.", descriptionView); //$NON-NLS-1$
    descriptionView = new BasicItemDescriptionView();
    return descriptionView;
  }

  public JComponent getComponent() {
    return descriptionView.getComponent();
  }
}