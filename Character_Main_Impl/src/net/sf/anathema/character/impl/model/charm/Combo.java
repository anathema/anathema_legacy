package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.impl.model.charm.combo.IComboArbitrator;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboModelListener;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Combo implements ICombo {
  private final List<ICharm> charmList = new ArrayList<ICharm>();
  private final List<IComboModelListener> comboModelListeners = new ArrayList<IComboModelListener>();
  private ICharm extraActionCharm = null;
  private ICharm simpleCharm = null;
  private final ISimpleTextualDescription name = new SimpleTextualDescription();
  private final ISimpleTextualDescription description = new SimpleTextualDescription();
  private Integer id = null;
  private final IComboArbitrator rules;

  public Combo(IComboArbitrator rules) {
    this.rules = rules;
  }

  public ICharm[] getCharms() {
    return charmList.toArray(new ICharm[0]);
  }

  public void addCharm(ICharm charm) {
    if (checkRules(charm)) {
      addCharmNoValidate(charm);
    }
    else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo."); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  private void addCharmNoValidate(ICharm charm) {
    charmList.add(charm);
    if (charm.getCharmType() == CharmType.Simple) {
      simpleCharm = charm;
    }
    if (charm.getCharmType() == CharmType.ExtraAction) {
      extraActionCharm = charm;
    }
    fireComboChanged();
  }

  public synchronized void addComboModelListener(IComboModelListener listener) {
    comboModelListeners.add(listener);
  }

  private boolean checkRules(ICharm addedCharm) {
    boolean legal = rules.isCharmComboLegal(addedCharm);
    for (ICharm charm : getCharms()) {
      legal = legal && rules.isComboLegal(charm, addedCharm);
    }
    return legal;
  }

  private synchronized void fireComboChanged() {
    List<IComboModelListener> cloneList = new ArrayList<IComboModelListener>(comboModelListeners);
    for (IComboModelListener listener : cloneList) {
      listener.comboChanged();
    }
  }

  public ICharm[] getComboLegalCharms(ICharm[] learnedCharms) {
    List<ICharm> legalCharms = new ArrayList<ICharm>(Arrays.asList(learnedCharms));
    for (ICharm charm : learnedCharms) {
      if (!checkRules(charm)) {
        legalCharms.remove(charm);
      }
    }
    return legalCharms.toArray(new ICharm[0]);
  }

  public void removeCharms(ICharm[] charms) {
    List<ICharm> removal = Arrays.asList(charms);
    charmList.removeAll(removal);
    if (simpleCharm != null && removal.contains(simpleCharm)) {
      simpleCharm = null;
    }
    if (extraActionCharm != null && removal.contains(extraActionCharm)) {
      extraActionCharm = null;
    }
    fireComboChanged();
  }

  @Override
  public ICombo clone() {
    Combo clone = new Combo(rules);
    copyCombo(this, clone);
    return clone;
  }

  public void getValuesFrom(ICombo combo) {
    this.clear();
    copyCombo(combo, this);
  }

  private void copyCombo(ICombo source, Combo destination) {
    for (ICharm charm : source.getCharms()) {
      destination.addCharmNoValidate(charm);
    }
    if (source.getId() != null) {
      destination.setId(source.getId());
    }
    destination.name.setText(source.getName().getText());
    destination.description.setText(source.getDescription().getText());
  }

  public void clear() {
    id = null;
    name.setText(""); //$NON-NLS-1$
    description.setText(""); //$NON-NLS-1$
    removeCharms(charmList.toArray(new ICharm[0]));
  }

  public ISimpleTextualDescription getName() {
    return name;
  }

  public ISimpleTextualDescription getDescription() {
    return description;
  }

  public boolean isComboLegal(ICharm charm) {
    return checkRules(charm);
  }

  public boolean contains(ICharm charm) {
    return charmList.contains(charm);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    Ensure.ensureNull("Id already set.", this.id); //$NON-NLS-1$
    Ensure.ensureNotNull("Id must not be null.", id); //$NON-NLS-1$
    setIdNoValidate(id);
  }

  private void setIdNoValidate(Integer id) {
    this.id = id;
  }
}