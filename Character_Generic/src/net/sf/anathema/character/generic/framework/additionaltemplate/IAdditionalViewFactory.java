package net.sf.anathema.character.generic.framework.additionaltemplate;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public interface IAdditionalViewFactory<T> {

  void createView(IAdditionalModel model, Resources resources, ICharacterType type, Object view);

  IView createView(IAdditionalModel model, Resources resources, ICharacterType characterType);

  Class<T> getViewClass();
}