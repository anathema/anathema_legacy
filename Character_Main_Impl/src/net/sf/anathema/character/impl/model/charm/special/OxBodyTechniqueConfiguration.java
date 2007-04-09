package net.sf.anathema.character.impl.model.charm.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.charm.OxBodyCategory;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.model.health.IHealthLevelProvider;
import net.sf.anathema.character.model.health.IOxBodyTechniqueArbitrator;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class OxBodyTechniqueConfiguration implements IOxBodyTechniqueConfiguration {

  private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();
  private final IIncrementChecker incrementChecker;
  private final OxBodyCategory[] categories;
  private final ICharm oxBodyTechnique;
  private final IHealthLevelProvider healthLevelProvider;

  public OxBodyTechniqueConfiguration(
      ITraitContext context,
      ICharm oxBodyTechnique,
      final IGenericTrait relevantTrait,
      final IOxBodyTechniqueArbitrator arbitrator,
      IOxBodyTechniqueCharm properties) {
    this.oxBodyTechnique = oxBodyTechnique;
    incrementChecker = new IIncrementChecker() {
      public boolean isValidIncrement(int increment) {
        return increment < 0
            || (arbitrator.isIncrementAllowed(increment) && getCurrentLearnCount() + increment <= relevantTrait.getCurrentValue());
      }
    };
    Set<String> ids = properties.getHealthLevels().keySet();
    List<OxBodyCategory> categoryList = new ArrayList<OxBodyCategory>(ids.size());
    for (String id : ids) {
      categoryList.add(new OxBodyCategory(context, properties.getHealthLevels().get(id), id, incrementChecker));
    }
    categories = categoryList.toArray(new OxBodyCategory[categoryList.size()]);
    for (OxBodyCategory category : categories) {
      category.addCurrentValueListener(new IIntValueChangedListener() {
        public void valueChanged(int newValue) {
          fireLearnCountChanged(getCurrentLearnCount());
        }
      });
    }
    this.healthLevelProvider = new OxBodyTechniqueHealthLevelProvider(categories);
  }

  @Override
  public void forget() {
    for (OxBodyCategory category : getCategories()) {
      category.setCurrentValue(0);
    }
  }

  @Override
  public void learn(boolean experienced) {
    OxBodyCategory trait = getCategories()[0];
    if (experienced && getCurrentLearnCount() == 0) {
      trait.setExperiencedValue(1);
    }
    else if (getCreationLearnCount() == 0) {
      trait.setCreationValue(1);
    }
  }

  public OxBodyCategory[] getCategories() {
    return categories;
  }

  public int getCreationLearnCount() {
    int sum = 0;
    for (OxBodyCategory category : getCategories()) {
      sum += category.getCreationValue();
    }
    return sum;
  }

  public int getCurrentLearnCount() {
    int sum = 0;
    for (OxBodyCategory category : getCategories()) {
      sum += category.getCurrentValue();
    }
    return sum;
  }

  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  private void fireLearnCountChanged(final int learnCount) {
    control.forAllDo(new IClosure<ISpecialCharmLearnListener>() {
      public void execute(ISpecialCharmLearnListener input) {
        input.learnCountChanged(learnCount);
      }
    });
  }

  public ICharm getCharm() {
    return oxBodyTechnique;
  }

  public OxBodyCategory getCategoryById(String id) {
    for (OxBodyCategory category : categories) {
      if (category.getId().equals(id)) {
        return category;
      }
    }
    throw new IllegalArgumentException("No ox body category found with id " + id); //$NON-NLS-1$
  }

  public IHealthLevelProvider getHealthLevelProvider() {
    return healthLevelProvider;
  }
}