package net.sf.anathema.character.intimacies.presenter;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DemoIntimaciesModel implements IIntimaciesModel, IIntimaciesAdditionalModel {

  private List<IIntimacy> entries = new ArrayList<IIntimacy>();

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  public void addModelChangeListener(IChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  public int getCompletionValue() {
    throw new UnsupportedOperationException();
  }

  public int getFreeIntimacies() {
    throw new UnsupportedOperationException();
  }

  public int getIntimaciesLimit() {
    throw new UnsupportedOperationException();
  }

  public void setCurrentName(String name) {
    throw new UnsupportedOperationException();
  }

  public void addModelChangeListener(IRemovableEntryListener<IIntimacy> listener) {
    throw new UnsupportedOperationException();
  }

  public IIntimacy commitSelection() {
    throw new UnsupportedOperationException();
  }

  public List<IIntimacy> getEntries() {
    return entries ;
  }

  public void removeEntry(IIntimacy entry) {
    throw new UnsupportedOperationException();
  }

  public void addChangeListener(IChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Concept;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public String getTemplateId() {
    return IntimaciesTemplate.ID;
  }

  public void addEntry(IIntimacy intimacy) {
    entries.add(intimacy);
  }

  public IIntimaciesModel getIntimaciesModel() {
    return this;
  }
}