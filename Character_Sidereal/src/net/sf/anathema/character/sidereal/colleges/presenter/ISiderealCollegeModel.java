package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;

public interface ISiderealCollegeModel extends IAdditionalModel {

  IAstrologicalHouse[] getAllHouses();

  int getTotalFavoredDotCount();

  int getTotalGeneralDotCount();

  void addCharacterChangeListener(ICharacterChangeListener listener);
}