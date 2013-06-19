package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.removableentry.presenter.RemovableEntryChangeAdapter;
import net.sf.anathema.character.main.hero.CharacterModelGroup;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.lib.control.IChangeListener;

public class IntimaciesAdditionalModel implements IIntimaciesAdditionalModel {
  private final IIntimaciesModel model;
  private final IAdditionalTemplate additionalTemplate;

  protected IntimaciesAdditionalModel(IAdditionalTemplate additionalTemplate, IIntimaciesModel model) {
    this.additionalTemplate = additionalTemplate;
    this.model = model;
  }

  public IntimaciesAdditionalModel(IAdditionalTemplate additionalTemplate, Hero hero) {
    this(additionalTemplate, new IntimaciesModel(hero));
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    model.addModelChangeListener(new RemovableEntryChangeAdapter<IIntimacy>(listener));
    model.addModelChangeListener(listener);
  }

  @Override
  public CharacterModelGroup getAdditionalModelType() {
    return CharacterModelGroup.SpiritualTraits;
  }

  @Override
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new IntimaciesBonusPointCalculator(model);
  }

  @Override
  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  @Override
  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  @Override
  public IIntimaciesModel getIntimaciesModel() {
    return model;
  }
}