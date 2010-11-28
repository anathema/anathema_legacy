package net.sf.anathema.character.generic.framework.magic.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractMagicLearnProperties implements IMagicLearnProperties {

  private final IResources resources;

  public AbstractMagicLearnProperties(IResources resources) {
    this.resources = resources;
  }

  public Icon getAddButtonIcon() {
    return new BasicUi(resources).getRightArrowIcon();
  }

  public Icon getRemoveButtonIcon() {
    return new BasicUi(resources).getRemoveIcon();
  }

  protected final IResources getResources() {
    return resources;
  }

  public ListCellRenderer getLearnedMagicRenderer() {
    return new IdentificateListCellRenderer(getResources());
  }
}