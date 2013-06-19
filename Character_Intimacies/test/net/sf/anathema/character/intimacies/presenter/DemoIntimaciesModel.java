package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.HeroModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullHeroModelExperienceCalculator;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class DemoIntimaciesModel implements IIntimaciesModel, IIntimaciesAdditionalModel {

  private List<IIntimacy> entries = new ArrayList<>();

  @Override
  public void addModelChangeListener(IChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isCharacterExperienced() {
    return false;
  }

  @Override
  public int getCompletionValue() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getFreeIntimacies() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getIntimaciesLimit() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setCurrentName(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addChangeListener(FlavoredChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addModelChangeListener(IRemovableEntryListener<IIntimacy> listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IIntimacy commitSelection() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<IIntimacy> getEntries() {
    return entries;
  }

  @Override
  public void removeEntry(IIntimacy entry) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HeroModelGroup getAdditionalModelType() {
    return HeroModelGroup.Outline;
  }

  @Override
  public HeroModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  @Override
  public HeroModelExperienceCalculator getExperienceCalculator() {
    return new NullHeroModelExperienceCalculator();
  }

  @Override
  public String getTemplateId() {
    return IntimaciesTemplate.ID;
  }

  public void addEntry(IIntimacy intimacy) {
    entries.add(intimacy);
  }

  @Override
  public IIntimaciesModel getIntimaciesModel() {
    return this;
  }
}