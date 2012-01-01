package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.impl.model.charm.combo.FirstEditionComboArbitrator;
import net.sf.anathema.character.impl.model.charm.combo.IComboArbitrator;
import net.sf.anathema.character.impl.model.charm.combo.SecondEditionComboArbitrator;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.IComboConfigurationListener;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ComboConfiguration implements IComboConfiguration {

  private final List<ICombo> creationComboList = new ArrayList<ICombo>();
  private final List<ICombo> experiencedComboList = new ArrayList<ICombo>();
  private final IComboArbitrator rules;
  private final ICombo editCombo = new Combo();
  private final GenericControl<IComboConfigurationListener> control = new GenericControl<IComboConfigurationListener>();
  private final ComboIdProvider idProvider = new ComboIdProvider();
  private final IComboLearnStrategy learnStrategy;
  private ExperienceComboEditingSupport experienceSupport;


  public ComboConfiguration(
          ICharmConfiguration charmConfiguration,
          IComboLearnStrategy learnStrategy,
          IExaltedEdition edition,
          IExperiencePointConfiguration experiencePoints) {
    this.learnStrategy = learnStrategy;
    charmConfiguration.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm charm) {
        checkCombos(charm);
      }
    });
    final IComboArbitrator[] editionRules = new IComboArbitrator[1];
    edition.accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        editionRules[0] = new FirstEditionComboArbitrator();
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        editionRules[0] = new SecondEditionComboArbitrator();
      }
    });
    this.rules = editionRules[0];
    this.experienceSupport = new ExperienceComboEditingSupport(experiencePoints, editCombo, this);
  }

  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    rules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  private void checkCombos(ICharm charm) {
    List<ICombo> deletionList = new ArrayList<ICombo>();
    for (ICombo combo : creationComboList) {
      if (combo.contains(charm)) {
        combo.removeCharms(new ICharm[]{charm});
        if (combo.getCharms().length < 2) {
          deletionList.add(combo);
        }
        fireComboChanged(combo);
      }
    }
    if (editCombo.contains(charm)) {
      removeCharmsFromCombo(new ICharm[]{charm});
    }
    for (ICombo combo : deletionList) {
      deleteCombo(combo);
    }
  }

  public void addCharmToCombo(ICharm charm) {
    if (rules.canBeAddedToCombo(getEditCombo(), charm)) {
      getEditCombo().addCharm(charm);
    } else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo."); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  public void addComboModelListener(IChangeListener listener) {
    editCombo.addComboModelListener(listener);
  }

  public void removeCharmsFromCombo(ICharm[] charms) {
    editCombo.removeCharms(charms);
  }

  public void finalizeComboXP(String xpMessage) {
    experienceSupport.commitChanges(xpMessage);
    finalizeCombo();
  }

  public void finalizeCombo() {
    learnStrategy.finalizeCombo(this);
  }

  public void finalizeCombo(boolean experienced) {
    ICombo combo = editCombo.clone();
    if (combo.getId() == null) {
      combo.setId(idProvider.createId());
      if (experienced) {
        experiencedComboList.add(combo);
      } else {
        creationComboList.add(combo);
      }
      fireComboAdded(combo);
    } else {
      ICombo editedCombo = getComboById(combo.getId());
      editedCombo.getValuesFrom(combo);
      fireComboChanged(editedCombo);
      fireEndEditEvent();
    }
    editCombo.clear();
  }

  public ICombo getEditCombo() {
    return editCombo;
  }

  private ICombo getComboById(int id) {
    for (ICombo combo : getCurrentCombos()) {
      if (combo.getId() == id) {
        return combo;
      }
    }
    return null;
  }

  public void addComboConfigurationListener(IComboConfigurationListener listener) {
    control.addListener(listener);
  }

  private void fireComboAdded(final ICombo combo) {
    control.forAllDo(new IClosure<IComboConfigurationListener>() {
      public void execute(IComboConfigurationListener input) {
        input.comboAdded(combo);
      }
    });
  }

  private void fireComboDeleted(final ICombo combo) {
    control.forAllDo(new IClosure<IComboConfigurationListener>() {
      public void execute(IComboConfigurationListener input) {
        input.comboDeleted(combo);
      }
    });
  }

  private void fireComboChanged(final ICombo combo) {
    control.forAllDo(new IClosure<IComboConfigurationListener>() {
      public void execute(IComboConfigurationListener input) {
        input.comboChanged(combo);
      }
    });
  }

  private void fireBeginEditEvent(final ICombo combo) {
    control.forAllDo(new IClosure<IComboConfigurationListener>() {
      public void execute(IComboConfigurationListener input) {
        input.editBegun(combo);
      }
    });
  }

  private void fireEndEditEvent() {
    control.forAllDo(new IClosure<IComboConfigurationListener>() {
      public void execute(IComboConfigurationListener input) {
        input.editEnded();
      }
    });
  }

  public ICombo[] getCurrentCombos() {
    return learnStrategy.getCurrentCombos(this);
  }

  public ICombo[] getCreationCombos() {
    return creationComboList.toArray(new ICombo[creationComboList.size()]);
  }

  public ICombo[] getExperienceLearnedCombos() {
    return experiencedComboList.toArray(new ICombo[experiencedComboList.size()]);
  }

  public boolean isComboLegal(ICharm charm) {
    return rules.canBeAddedToCombo(getEditCombo(), charm);
  }

  public void deleteCombo(ICombo combo) {
    experiencedComboList.remove(combo);
    creationComboList.remove(combo);
    fireComboDeleted(combo);
    if (combo.getId().equals(editCombo.getId())) {
      clearCombo();
    }
  }

  public void clearCombo() {
    editCombo.clear();
    experienceSupport.abortChange();
    fireEndEditEvent();
  }

  public void beginComboEdit(ICombo combo) {
    editCombo.clear();
    editCombo.getValuesFrom(combo);
    experienceSupport.startChanging(combo);
    fireBeginEditEvent(combo);
  }

  public boolean isLearnedOnCreation(ICombo combo) {
    return creationComboList.contains(combo);
  }

  @Override
  public boolean isAllowedToRemove(ICharm charm) {
    return experienceSupport.isAllowedToRemove(charm);
  }

  @Override
  public boolean canFinalizeWithXP() {
    return experienceSupport.canFinalizeWithXP();
  }
}