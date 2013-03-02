package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public abstract class AbstractMagicLearnProperties implements IMagicLearnProperties {

  private final IResources resources;

  public AbstractMagicLearnProperties(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Icon getAddButtonIcon() {
    return new BasicUi(resources).getRightArrowIcon();
  }

  @Override
  public Icon getRemoveButtonIcon() {
    return new BasicUi(resources).getRemoveIcon();
  }

  protected final IResources getResources() {
    return resources;
  }

  @Override
  public ListCellRenderer getLearnedMagicRenderer() {
    return new IdentificateListCellRenderer(getResources());
  }
}