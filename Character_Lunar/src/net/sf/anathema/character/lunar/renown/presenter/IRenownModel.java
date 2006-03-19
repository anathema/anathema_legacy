package net.sf.anathema.character.lunar.renown.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.lunar.renown.model.RenownType;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IRenownModel extends IAdditionalModel {

  public ITrait getTrait(RenownType type);

  public int calculateTotalRenown();

  public ITrait[] getAllTraits();

  public int getMaximumAllowedFaceRank();

  public int getUltimateFaceRank();

  public ITrait getFace();

  public void addCharacterChangeListener(ICharacterChangeListener adapter);

  public int calculateAlottedRenownPoints();

  public void addRenownChangedListener(IIntValueChangedListener listener);

  public void addFreeRenownPointsChangedListener(IIntValueChangedListener listener);

  public boolean isCharacterExperienced();
}