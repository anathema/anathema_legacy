package net.sf.anathema.character.lunar.renown.model;

import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;

public class FaceValueChangeChecker implements IValueChangeChecker {

  private final IRenownModel model;

  public FaceValueChangeChecker(IRenownModel model) {
    this.model = model;
  }

  public boolean isValidNewValue(int value) {
    return value <= model.getMaximumAllowedFaceRank();
  }
}