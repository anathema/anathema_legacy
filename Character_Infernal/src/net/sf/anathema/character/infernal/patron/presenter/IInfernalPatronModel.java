package net.sf.anathema.character.infernal.patron.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;

public interface IInfernalPatronModel extends IAdditionalModel {

  IFavorableDefaultTrait[] getAllYozis();
  
  ICharacterModelContext getContext();

  String getPatronYozi();

  FavorableState getFavorableState(ITraitType yozi);

  boolean isPatronYozi(ITraitType type);

  boolean isCasteYozi(ITraitType type);

  void setPatronYozi(ITraitType type, boolean newValue);
}