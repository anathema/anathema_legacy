package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public abstract class VirtueFlawModel implements IVirtueFlawModel {

  private final String templateId;
  private final IVirtueFlaw virtueFlaw = new VirtueFlaw();
  private final ICharacterModelContext context;

  public VirtueFlawModel(final ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    this.context = context;
    this.templateId = additionalTemplate.getId();
  }

  public boolean isVirtueFlawChangable() {
    return !getContext().getBasicCharacterContext().isExperienced();
  }

  public IVirtueFlaw getVirtueFlaw() {
    return virtueFlaw;
  }

  public String getTemplateId() {
    return templateId;
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Advantages;
  }

  public void addBonusPointsChangeListener(IChangeListener listener) {
    // Nothing to do
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public void addVirtueChangeListener(VirtueChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  protected ICharacterModelContext getContext() {
    return context;
  }

  public void addVirtueFlawChangableListener(final IBooleanValueChangedListener listener) {
    getContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        listener.valueChanged(isVirtueFlawChangable());
      }
    });
  }
}