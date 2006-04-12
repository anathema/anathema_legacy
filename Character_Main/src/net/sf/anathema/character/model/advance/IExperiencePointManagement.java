package net.sf.anathema.character.model.advance;

import net.sf.anathema.character.presenter.overview.IValueModel;

public interface IExperiencePointManagement {

  public int getMiscGain();

  public int getTotalCosts();

  public IValueModel<Integer> getAttributeModel();

  public IValueModel<Integer> getMiscModel();

  public IValueModel<Integer> getEssenceModel();

  public IValueModel<Integer> getWillpowerModel();

  public IValueModel<Integer> getVirtueModel();

  public IValueModel<Integer> getSpellModel();

  public IValueModel<Integer> getComboModel();

  public IValueModel<Integer> getCharmModel();

  public IValueModel<Integer> getAbilityModel();

  public IValueModel<Integer> getSpecialtyModel();
}