package net.sf.anathema.character.meritsflaws.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class MeritsFlawsAdditionalModel implements IMeritsFlawsAdditionalModel {

  private final IMeritsFlawsModel model;
  private final IAdditionalTemplate additionalTemplate;

  public MeritsFlawsAdditionalModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      IChangeListener[] listeners) {
    this.additionalTemplate = additionalTemplate;
    this.model = new MeritsFlawsModel(context);
    for (IChangeListener listener : listeners) {
      model.addModelChangeListener(listener);
    }
  }

  public IMeritsFlawsModel getMeritsFlawsModel() {
    return model;
  }

  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Advantages;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new MeritsFlawsBonusPointCalculator(model);
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    model.addModelChangeListener(listener);
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new MeritsFlawsExperiencePointCalculator(model);
  }
}