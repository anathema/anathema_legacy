package net.sf.anathema.character.craft.model;

import net.sf.anathema.character.craft.ICraftAdditionalModel;
import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CraftAdditionalModel implements ICraftAdditionalModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ICraftModel model;

  public CraftAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    this.model = new CraftModel(context);
  }

  public ICraftModel getCraftModel() {
    return model;
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    //nothing to do;
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Abilities;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public String getTemplateId() {
    return additionalTemplate.getId();
  }
}