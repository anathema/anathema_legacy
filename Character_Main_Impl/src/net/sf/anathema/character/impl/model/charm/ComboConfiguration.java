package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.charm.combo.ComboArbitrator;
import net.sf.anathema.character.impl.model.charm.combo.IComboArbitrator;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.IComboConfigurationListener;
import net.sf.anathema.character.model.charm.IComboModelListener;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;

public class ComboConfiguration implements IComboConfiguration {

  private final List<ICombo> creationComboList = new ArrayList<ICombo>();
  private final List<ICombo> experiencedComboList = new ArrayList<ICombo>();
  private final IComboArbitrator rules = new ComboArbitrator();
  private final ICombo editCombo = new Combo(rules);
  private final List<IComboConfigurationListener> listeners = new ArrayList<IComboConfigurationListener>();
  private final ICharmConfiguration charmConfiguration;
  private final ComboIdProvider idProvider = new ComboIdProvider();
  private final IComboLearnStrategy learnStrategy;

  public ComboConfiguration(ICharmConfiguration charmConfiguration, IComboLearnStrategy learnStrategy) {
    this.charmConfiguration = charmConfiguration;
    this.learnStrategy = learnStrategy;
    this.charmConfiguration.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm charm) {
        checkCombos(charm);
      }
    });
  }

  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    rules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  private void checkCombos(ICharm charm) {
    List<ICombo> deletionList = new ArrayList<ICombo>();
    for (ICombo combo : creationComboList) {
      if (combo.contains(charm)) {
        combo.removeCharms(new ICharm[] { charm });
        if (combo.getCharms().length < 2) {
          deletionList.add(combo);
        }
        fireComboChanged(combo);
      }
    }
    if (editCombo.contains(charm)) {
      removeCharmsFromCombo(new ICharm[] { charm });
    }
    for (ICombo combo : deletionList) {
      deleteCombo(combo);
    }
  }

  private void fireComboChanged(ICombo combo) {
    for (IComboConfigurationListener listener : new ArrayList<IComboConfigurationListener>(listeners)) {
      listener.comboChanged(combo);
    }
  }

  public void addCharmToCombo(ICharm charm) {
    editCombo.addCharm(charm);
  }

  public void addComboModelListener(IComboModelListener listener) {
    editCombo.addComboModelListener(listener);
  }

  public void removeCharmsFromCombo(ICharm[] charms) {
    editCombo.removeCharms(charms);
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
      }
      else {
        creationComboList.add(combo);
      }
      fireComboAdded(combo);
    }
    else {
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

  public synchronized void addComboConfigurationListener(IComboConfigurationListener listener) {
    listeners.add(listener);
  }

  private synchronized void fireComboAdded(ICombo combo) {
    for (IComboConfigurationListener listener : new ArrayList<IComboConfigurationListener>(listeners)) {
      listener.comboAdded(combo);
    }
  }

  private synchronized void fireComboDeleted(ICombo combo) {
    for (IComboConfigurationListener listener : new ArrayList<IComboConfigurationListener>(listeners)) {
      listener.comboDeleted(combo);
    }
  }

  public ICombo[] getCurrentCombos() {
    return learnStrategy.getCurrentCombos(this);
  }

  public ICombo[] getCreationCombos() {
    return creationComboList.toArray(new ICombo[0]);
  }

  public ICombo[] getExperienceLearnedCombos() {
    return experiencedComboList.toArray(new ICombo[0]);
  }

  public boolean isComboLegal(ICharm charm) {
    return getEditCombo().isComboLegal(charm);
  }

  public void deleteCombo(ICombo combo) {
    experiencedComboList.remove(combo);
    creationComboList.remove(combo);
    fireComboDeleted(combo);
    if (combo.getId() == editCombo.getId()) {
      clearCombo();
    }
  }

  public void clearCombo() {
    editCombo.clear();
    fireEndEditEvent();
  }

  public void beginComboEdit(ICombo combo) {
    editCombo.clear();
    editCombo.getValuesFrom(combo);
    fireBeginEditEvent(combo);
  }

  private void fireBeginEditEvent(ICombo combo) {
    for (IComboConfigurationListener listener : new ArrayList<IComboConfigurationListener>(listeners)) {
      listener.editBegun(combo);
    }
  }

  private void fireEndEditEvent() {
    for (IComboConfigurationListener listener : new ArrayList<IComboConfigurationListener>(listeners)) {
      listener.editEnded();
    }
  }

  public boolean isLearnedOnCreation(ICombo combo) {
    return creationComboList.contains(combo);
  }
}