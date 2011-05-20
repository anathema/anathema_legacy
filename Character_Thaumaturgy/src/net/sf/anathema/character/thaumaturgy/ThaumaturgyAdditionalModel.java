package net.sf.anathema.character.thaumaturgy;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyAdditionalModel;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyModel;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyBonusPointCalculator;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyExperiencePointCalculator;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ThaumaturgyAdditionalModel implements IThaumaturgyAdditionalModel {

  private final IAdditionalTemplate additionalTemplate;
  private final IThaumaturgyModel model;

  public ThaumaturgyAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    this.model = new ThaumaturgyModel(context.getTraitCollection(), context);
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Abilities;
  }

  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  public IThaumaturgyModel getThaumaturgyModel() {
    return model;
  }

  public void addChangeListener(IChangeListener listener)
  {
	  model.addChangeListener(listener);
  }

	@Override
	public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
		return new ThaumaturgyBonusPointCalculator(model);
	}
	
	@Override
	public IAdditionalModelExperienceCalculator getExperienceCalculator() {
		return new ThaumaturgyExperiencePointCalculator(model);
	}
}