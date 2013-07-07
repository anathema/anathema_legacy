package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.combos.CombosModel;
import net.sf.anathema.character.main.CharacterChangeComboListener;
import net.sf.anathema.character.main.charm.CharmLearnAdapter;
import net.sf.anathema.character.main.charm.Combo;
import net.sf.anathema.character.main.charm.ComboIdProvider;
import net.sf.anathema.character.main.charm.ICombo;
import net.sf.anathema.character.main.charm.IComboConfigurationListener;
import net.sf.anathema.character.main.charm.combo.IComboArbitrator;
import net.sf.anathema.character.main.charm.combo.SecondEditionComboArbitrator;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class CombosModelImpl implements CombosModel {

  private final List<ICombo> comboList = new ArrayList<>();
  private final IComboArbitrator rules =  new SecondEditionComboArbitrator();
  private final ICombo editCombo = new Combo();
  private final Announcer<IComboConfigurationListener> control = Announcer.to(IComboConfigurationListener.class);
  private final ComboIdProvider idProvider = new ComboIdProvider();
  private Hero hero;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.hero = hero;
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    CharmsModelFetcher.fetch(hero).addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm charm) {
        checkCombos(charm);
      }
    });
    addComboConfigurationListener(new CharacterChangeComboListener(hero.getChangeAnnouncer()));
  }

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    rules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  private void checkCombos(ICharm charm) {
    List<ICombo> deletionList = new ArrayList<>();
    for (ICombo combo : comboList) {
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

  @Override
  public void addCharmToCombo(ICharm charm, boolean experienced) {
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
  public void removeCharmsFromCombo(ICharm[] charms) {
    editCombo.removeCharms(charms);
  }

  @Override
  public void finalizeCombo() {
    ICombo combo = editCombo.clone();
    if (combo.getId() == null) {
      combo.setId(idProvider.createId());
      comboList.add(combo);
      fireComboAdded(combo);
    } else {
      ICombo editedCombo = getComboById(combo.getId());
      editedCombo.getValuesFrom(combo);
      fireComboChanged(editedCombo);
      fireEndEditEvent();
    }
    editCombo.clear();
  }

  @Override
  public ICombo getEditCombo() {
    return editCombo;
  }

  private ICombo getComboById(int id) {
    for (ICombo combo : getAllCombos()) {
      if (combo.getId() == id) {
        return combo;
      }
    }
    return null;
  }

  @Override
  public void addComboConfigurationListener(IComboConfigurationListener listener) {
    control.addListener(listener);
  }

  private void fireComboAdded(ICombo combo) {
    control.announce().comboAdded(combo);
  }

  private void fireComboDeleted(ICombo combo) {
    control.announce().comboDeleted(combo);
  }

  private void fireComboChanged(ICombo combo) {
    control.announce().comboChanged(combo);
  }

  private void fireBeginEditEvent(ICombo combo) {
    control.announce().editBegun(combo);
  }

  private void fireEndEditEvent() {
    control.announce().editEnded();
  }

  @Override
  public ICombo[] getAllCombos() {
    return comboList.toArray(new ICombo[comboList.size()]);
  }

  @Override
  public boolean isComboLegal(ICharm charm) {
    return rules.canBeAddedToCombo(getEditCombo(), charm);
  }

  @Override
  public void deleteCombo(ICombo combo) {
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
  public void beginComboEdit(ICombo combo) {
    editCombo.clear();
    editCombo.getValuesFrom(combo);
    fireBeginEditEvent(combo);
  }
}