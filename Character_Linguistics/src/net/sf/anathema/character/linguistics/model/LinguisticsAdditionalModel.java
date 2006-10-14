package net.sf.anathema.character.linguistics.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class LinguisticsAdditionalModel implements ILinguisticsAdditionalModel {

  private final IAdditionalTemplate template;
  private final ILinguisticsModel model;

  public LinguisticsAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.template = additionalTemplate;
    this.model = new LinguisticsModel(context);
  }

  public ILinguisticsModel getLinguisticsModel() {
    return model;
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
    return template.getId();
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    // nothing to do    
  }
}