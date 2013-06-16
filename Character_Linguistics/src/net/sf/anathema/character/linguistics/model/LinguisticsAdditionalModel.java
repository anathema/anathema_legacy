package net.sf.anathema.character.linguistics.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.removableentry.presenter.RemovableEntryChangeAdapter;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;

public class LinguisticsAdditionalModel extends AbstractAdditionalModelAdapter implements ILinguisticsAdditionalModel {

  private final IAdditionalTemplate template;
  private final ILinguisticsModel model;

  public LinguisticsAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.template = additionalTemplate;
    this.model = new LinguisticsModel(context);
  }

  @Override
  public ILinguisticsModel getLinguisticsModel() {
    return model;
  }

  @Override
  public CharacterModelGroup getAdditionalModelType() {
    return CharacterModelGroup.NaturalTraits;
  }

  @Override
  public String getTemplateId() {
    return template.getId();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    model.addModelChangeListener(new RemovableEntryChangeAdapter<Identifier>(listener));
  }
}