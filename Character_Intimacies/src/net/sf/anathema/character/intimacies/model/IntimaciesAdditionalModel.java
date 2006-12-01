package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class IntimaciesAdditionalModel implements IIntimaciesAdditionalModel {
  private final IIntimaciesModel model;
  private final IAdditionalTemplate additionalTemplate;
  private final ChangeControl control = new ChangeControl();

  public IntimaciesAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    this.model = new IntimaciesModel(context);
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      public void entryAdded(IIntimacy entry) {
        control.fireChangedEvent();
      }

      public void entryAllowed(boolean complete) {
        // Nothing to do
      }

      public void entryRemoved(IIntimacy entry) {
        control.fireChangedEvent();
      }
    });
  }

  public void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Concept;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new IntimaciesBonusPointCalculator(model);
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  public IIntimaciesModel getIntimaciesModel() {
    return model;
  }
}