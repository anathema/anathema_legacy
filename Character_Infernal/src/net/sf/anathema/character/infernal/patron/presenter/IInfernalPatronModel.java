package net.sf.anathema.character.infernal.patron.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;

public interface IInfernalPatronModel extends IAdditionalModel {

  public IFavorableDefaultTrait[] getAllYozis();
  
  public ICharacterModelContext getContext();

  public String getFavoredYozi();
  
  public void setFavoredYozi(String yozi);

  public void addCharacterChangeListener(ICharacterChangeListener listener);
}