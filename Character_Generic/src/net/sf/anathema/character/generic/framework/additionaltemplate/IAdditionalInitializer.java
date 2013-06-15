package net.sf.anathema.character.generic.framework.additionaltemplate;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.Resources;

public interface IAdditionalInitializer<T> {

  void initialize(IAdditionalModel model, Resources resources, ICharacterType type, Object view);

  Class<T> getViewClass();
}