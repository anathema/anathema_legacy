package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.resources.BackgroundInternationalizer;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;


public class Displayer {
  private BackgroundInternationalizer internationalizer;

  public Displayer(BackgroundInternationalizer internationalizer) {
    this.internationalizer = internationalizer;
  }

  public Object getDisplayObject(Object anObject) {
    if (anObject instanceof IDefaultTrait) {
      anObject = ((IDefaultTrait) anObject).getType();
    }
    if (anObject instanceof IBackgroundTemplate) {
      return internationalizer.getDisplayName((IBackgroundTemplate) anObject);
    }
    return anObject;
  }
}