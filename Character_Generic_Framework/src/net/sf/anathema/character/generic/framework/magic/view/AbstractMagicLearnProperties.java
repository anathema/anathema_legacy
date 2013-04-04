package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public abstract class AbstractMagicLearnProperties implements IMagicLearnProperties {

  private final Resources resources;

  public AbstractMagicLearnProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Icon getAddButtonIcon() {
    return new BasicUi().getRightArrowIcon();
  }

  @Override
  public Icon getRemoveButtonIcon() {
    return new BasicUi().getRemoveIcon();
  }

  protected final Resources getResources() {
    return resources;
  }

  @Override
  public ListCellRenderer getLearnedMagicRenderer() {
    return new IdentificateListCellRenderer(getResources());
  }
}