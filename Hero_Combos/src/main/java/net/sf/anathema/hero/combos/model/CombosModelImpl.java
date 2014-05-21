package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.display.presenter.CharacterChangeComboListener;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationListener;
import net.sf.anathema.hero.combos.display.presenter.CombosModel;
import net.sf.anathema.hero.combos.model.rules.SecondEditionComboArbitrator;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class CombosModelImpl implements CombosModel {

  private final List<Combo> comboList = new ArrayList<>();
  private final ComboArbitrator rules =  new SecondEditionComboArbitrator();
  private final Combo editCombo = new ComboImpl();
  private final Announcer<ComboConfigurationListener> control = Announcer.to(ComboConfigurationListener.class);
  private final ComboIdProvider idProvider = new ComboIdProvider();
  private Hero hero;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    CharmsModelFetcher.fetch(hero).addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(Charm charm) {
        checkCombos(charm);
      }
    });
    addComboConfigurationListener(new CharacterChangeComboListener(hero.getChangeAnnouncer()));
  }

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    rules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  private void checkCombos(Charm charm) {
    List<Combo> deletionList = new ArrayList<>();
    for (Combo combo : comboList) {
      if (combo.contains(charm)) {
        combo.removeCharms(new Charm[]{charm});
        if (combo.getCharms().length < 2) {
          deletionList.add(combo);
        }
        fireComboChanged(combo);
      }
    }
    if (editCombo.contains(charm)) {
      removeCharmsFromCombo(new Charm[]{charm});
    }
    for (Combo combo : deletionList) {
      deleteCombo(combo);
    }
  }

  @Override
  public void addCharmToCombo(Charm charm, boolean experienced) {
    if (rules.canBeAddedToCombo(getEditCombo(), charm)) {
      getEditCombo().addCharm(charm, experienced);
    } else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo.");
    }
  }

  @Override
  public void addComboModelListener(ChangeListener listener) {
    editCombo.addComboModelListener(listener);
  }

  @Override
  public void removeCharmsFromCombo(Charm[] charms) {
    editCombo.removeCharms(charms);
  }

  @Override
  public void finalizeCombo() {
    Combo combo = editCombo.clone();
    if (combo.getId() == null) {
      combo.setId(idProvider.createId());
      comboList.add(combo);
      fireComboAdded(combo);
    } else {
      Combo editedCombo = getComboById(combo.getId());
      editedCombo.getValuesFrom(combo);
      fireComboChanged(editedCombo);
      fireEndEditEvent();
    }
    editCombo.clear();
  }

  @Override
  public Combo getEditCombo() {
    return editCombo;
  }

  private Combo getComboById(int id) {
    for (Combo combo : getAllCombos()) {
      if (combo.getId() == id) {
        return combo;
      }
    }
    return null;
  }

  @Override
  public void addComboConfigurationListener(ComboConfigurationListener listener) {
    control.addListener(listener);
  }

  private void fireComboAdded(Combo combo) {
    control.announce().comboAdded(combo);
  }

  private void fireComboDeleted(Combo combo) {
    control.announce().comboDeleted(combo);
  }

  private void fireComboChanged(Combo combo) {
    control.announce().comboChanged(combo);
  }

  private void fireBeginEditEvent(Combo combo) {
    control.announce().editBegun(combo);
  }

  private void fireEndEditEvent() {
    control.announce().editEnded();
  }

  @Override
  public Combo[] getAllCombos() {
    return comboList.toArray(new Combo[comboList.size()]);
  }

  @Override
  public boolean isComboLegal(Charm charm) {
    return rules.canBeAddedToCombo(getEditCombo(), charm);
  }

  @Override
  public void deleteCombo(Combo combo) {
    comboList.remove(combo);
    fireComboDeleted(combo);
    if (combo.getId().equals(editCombo.getId())) {
      clearCombo();
    }
  }

  @Override
  public void clearCombo() {
    editCombo.clear();
    fireEndEditEvent();
  }

  @Override
  public void beginComboEdit(Combo combo) {
    editCombo.clear();
    editCombo.getValuesFrom(combo);
    fireBeginEditEvent(combo);
  }
}