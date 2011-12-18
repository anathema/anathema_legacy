package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;

public class MutationsAdditionalModel implements IAdditionalModel {
  private final IMutationsModel model;
  private final IAdditionalTemplate additionalTemplate;

  public MutationsAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    this.model = new MutationsModel(context);
  }

  public IMutationsModel getModel() {
    return model;
  }

  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Advantages;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new MutationsBonusPointCalculator(model);
  }

  public void addChangeListener(IChangeListener listener) {
    model.addModelChangeListener(listener);
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }
}
