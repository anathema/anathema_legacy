package net.sf.anathema.character.impl.model.statistics;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.model.IExtendedConfiguration;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class ExtendedConfiguration implements IExtendedConfiguration {

  private final List<IAdditionalModel> additionalModels = new ArrayList<>();
  private Hero hero;
  private final List<IChangeListener> listeners = new ArrayList<>();

  public ExtendedConfiguration(Hero hero) {
    this.hero = hero;
  }

  public void addAdditionalModel(IAdditionalModelFactory factory, IAdditionalTemplate template) {
    IAdditionalModel model = factory.createModel(template, hero);
    for (IChangeListener listener : listeners) {
      model.addChangeListener(listener);
    }
    additionalModels.add(model);
  }

  @Override
  public IAdditionalModel[] getAdditionalModels() {
    return additionalModels.toArray(new IAdditionalModel[additionalModels.size()]);
  }

  @Override
  public IAdditionalModel[] getAdditionalModels(HeroModelGroup type) {
    List<IAdditionalModel> models = new ArrayList<>();
    for (IAdditionalModel model : additionalModels) {
      if (model.getAdditionalModelType() == type) {
        models.add(model);
      }
    }
    return models.toArray(new IAdditionalModel[models.size()]);
  }

  public void addAdditionalModelChangeListener(IChangeListener listener) {
    for (IAdditionalModel model : additionalModels) {
      model.addChangeListener(listener);
    }
    listeners.add(listener);
  }

  @Override
  public IAdditionalModel getAdditionalModel(String id) {
    for (IAdditionalModel model : getAdditionalModels()) {
      if (model.getTemplateId().equals(id)) {
        return model;
      }
    }
    return null;
  }
}