package net.sf.anathema.character.main.magic.view;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.ui.IdentifierConfiguration;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public abstract class AbstractMagicLearnProperties implements IMagicLearnProperties {

  private final Resources resources;

  public AbstractMagicLearnProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath getAddButtonIcon() {
    return new BasicUi().getRightArrowIconPath();
  }

  @Override
  public RelativePath getRemoveButtonIcon() {
    return new BasicUi().getLeftArrowIconPath();
  }

  protected final Resources getResources() {
    return resources;
  }

  @Override
  public AgnosticUIConfiguration<Identifier> getLearnedMagicRenderer() {
    return new IdentifierConfiguration(resources);
  }
}