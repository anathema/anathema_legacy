package net.sf.anathema.hero.magic.display;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractMagicLearnProperties implements MagicLearnProperties {

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
}