package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.main.hero.CharacterModelGroup;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;

public abstract class VirtueFlawModel extends AbstractAdditionalModelAdapter implements IVirtueFlawModel {

  private final String templateId;
  private final IVirtueFlaw virtueFlaw;
  private final ICharacterModelContext context;

  public VirtueFlawModel(ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    this.context = context;
    this.templateId = additionalTemplate.getId();
    virtueFlaw = new VirtueFlaw(context);
  }

  @Override
  public boolean isVirtueFlawChangable() {
    return !getContext().getBasicCharacterContext().isExperienced();
  }

  @Override
  public IVirtueFlaw getVirtueFlaw() {
    return virtueFlaw;
  }

  @Override
  public String getTemplateId() {
    return templateId;
  }

  @Override
  public CharacterModelGroup getAdditionalModelType() {
    return CharacterModelGroup.SpiritualTraits;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    GlobalChangeAdapter<String> adapter = new GlobalChangeAdapter<>(listener);
    getVirtueFlaw().addRootChangeListener(listener);
    getVirtueFlaw().getName().addTextChangedListener(adapter);
    getVirtueFlaw().getLimitTrait().addCurrentValueListener(adapter);
  }

  @Override
  public void addVirtueChangeListener(VirtueChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  protected ICharacterModelContext getContext() {
    return context;
  }

  @Override
  public void addVirtueFlawChangableListener(final IBooleanValueChangedListener listener) {
    getContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        listener.valueChanged(isVirtueFlawChangable());
      }
    });
  }
}