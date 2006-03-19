package net.sf.anathema.character.impl.model.statistics;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.model.IExtendedConfiguration;

public class ExtendedConfiguration implements IExtendedConfiguration {

  private final List<IAdditionalModel> additionalModels = new ArrayList<IAdditionalModel>();
  private final ICharacterModelContext context;
  private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

  public ExtendedConfiguration(ICharacterModelContext context) {
    this.context = context;
  }

  public void addAdditionalModel(IAdditionalModelFactory factory, IAdditionalTemplate template) {
    IAdditionalModel model = factory.createModel(
        template,
        context,
        listeners.toArray(new ChangeListener[listeners.size()]));
    additionalModels.add(model);
  }

  public IAdditionalModel[] getAdditionalModels() {
    return additionalModels.toArray(new IAdditionalModel[additionalModels.size()]);
  }

  public IAdditionalModel[] getAdditionalModels(AdditionalModelType type) {
    List<IAdditionalModel> models = new ArrayList<IAdditionalModel>();
    for (IAdditionalModel model : additionalModels) {
      if (model.getAdditionalModelType() == type) {
        models.add(model);
      }
    }
    return models.toArray(new IAdditionalModel[models.size()]);
  }

  public void addBonusPointsChangeListener(ChangeListener listener) {
    for (IAdditionalModel model : additionalModels) {
      model.addBonusPointsChangeListener(listener);
    }
    listeners.add(listener);
  }

  public IAdditionalModel getAdditionalModel(String id) {
    for (IAdditionalModel model : getAdditionalModels()) {
      if (model.getTemplateId().equals(id)) {
        return model;
      }
    }
    return null;
  }
}