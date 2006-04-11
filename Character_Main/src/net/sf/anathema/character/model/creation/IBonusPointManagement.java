package net.sf.anathema.character.model.creation;

import java.io.PrintStream;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.presenter.overview.IAlotmentModel;

public interface IBonusPointManagement {

  public void recalculate();

  public int getSpecialtyBonusPointCosts();

  public int getFavoredCharmPicksSpent();

  public int getDefaultCharmPicksSpent();

  public int getCharmBonusPointsSpent();

  public int getSpellBonusPointsSpent();

  public int getWillpowerBonusPointsSpent();

  public int getComboBonusPointsSpent();

  public int getEssenceBonusPointsSpent();

  public void dump(PrintStream printStream);

  public int getAdditionalBonusPointSpent();

  public int getAdditionalBonusPointAmount();

  public int getStandardBonusPointsSpent();

  public int getTotalBonusPointsSpent();

  public int getAdditionalMagicPointsSpent();

  public int getAdditionalMagicPointsAmount();

  public int getAdditionalModelTotalValue();

  /** Return the amount of unrestricted bonus points granted by additional models */
  public int getAdditionalGeneralBonusPoints();

  public IAlotmentModel getVirtueModel();

  public IAlotmentModel getBackgroundModel();

  public IAlotmentModel getDefaultAbilityModel();

  public IAlotmentModel getFavoredAbilityModel();

  public IAlotmentModel getFavoredAbilityPickModel();

  public IAlotmentModel getAttributeModel(AttributeGroupPriority priority);

}