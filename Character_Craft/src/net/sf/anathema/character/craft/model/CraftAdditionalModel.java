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
import net.sf.anathema.character.library.removableentry.presenter.RemovableEntryChangeAdapter;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.lib.control.IChangeListener;

public class CraftAdditionalModel implements ICraftAdditionalModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ICraftModel model;

  public CraftAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    this.model = new CraftModel(context);
  }

  @Override
  public ICraftModel getCraftModel() {
    return model;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    model.addModelChangeListener(new RemovableEntryChangeAdapter<ISubTrait>(listener));
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Abilities;
  }

  @Override
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  @Override
  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  @Override
  public String getTemplateId() {
    return additionalTemplate.getId();
  }
}