package net.sf.anathema.character.linguistics.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.removableentry.presenter.RemovableEntryChangeAdapter;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class LinguisticsAdditionalModel extends AbstractAdditionalModelAdapter implements ILinguisticsAdditionalModel {

  private final IAdditionalTemplate template;
  private final ILinguisticsModel model;

  public LinguisticsAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.template = additionalTemplate;
    this.model = new LinguisticsModel(context);
  }

  public ILinguisticsModel getLinguisticsModel() {
    return model;
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Abilities;
  }

  public String getTemplateId() {
    return template.getId();
  }

  public void addChangeListener(IChangeListener listener) {
    model.addModelChangeListener(new RemovableEntryChangeAdapter<IIdentificate>(listener));
  }
}