package net.sf.anathema.character.impl.model.charm;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combo implements ICombo {

  // Final fields were turned to volatile to allow clone to be implemented

  private volatile ChangeControl control = new ChangeControl();
  private volatile List<ICharm> creationCharmList = new ArrayList<ICharm>();
  private volatile List<ICharm> experiencedCharmList = new ArrayList<ICharm>();
  private ICharm extraActionCharm = null;
  private ICharm simpleCharm = null;
  private volatile ITextualDescription name = new SimpleTextualDescription();
  private volatile ITextualDescription description = new SimpleTextualDescription();
  private Integer id = null;

  @Override
  public ICharm[] getCharms() {
    ArrayList<ICharm> charms = new ArrayList<ICharm>();
    charms.addAll(creationCharmList);
    charms.addAll(experiencedCharmList);
    return charms.toArray(new ICharm[charms.size()]);
  }

  @Override
  public ICharm[] getCreationCharms() {
    return creationCharmList.toArray(new ICharm[creationCharmList.size()]);
  }

  @Override
  public ICharm[] getExperiencedCharms() {
    return experiencedCharmList.toArray(new ICharm[experiencedCharmList.size()]);
  }

  @Override
  public void addCharm(ICharm charm, boolean experienced) {
    List<ICharm> targetList = experienced ? experiencedCharmList : creationCharmList;
    targetList.add(charm);
    if (charm.getCharmTypeModel().getCharmType() == CharmType.Simple) {
      simpleCharm = charm;
    }
    if (charm.getCharmTypeModel().getCharmType() == CharmType.ExtraAction) {
      extraActionCharm = charm;
    }
    fireComboChanged();
  }

  @Override
  public void addComboModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  private void fireComboChanged() {
    control.fireChangedEvent();
  }

  @Override
  public void removeCharms(ICharm[] charms) {
    List<ICharm> removal = Arrays.asList(charms);
    creationCharmList.removeAll(removal);
    experiencedCharmList.removeAll(removal);
    if (simpleCharm != null && removal.contains(simpleCharm)) {
      simpleCharm = null;
    }
    if (extraActionCharm != null && removal.contains(extraActionCharm)) {
      extraActionCharm = null;
    }
    fireComboChanged();
  }

  @Override
  public Combo clone() {
    Combo clone;
    try {
      clone = (Combo) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.control = new ChangeControl();
    clone.creationCharmList = new ArrayList<ICharm>(creationCharmList.size());
    clone.experiencedCharmList = new ArrayList<ICharm>(experiencedCharmList.size());
    clone.name = new SimpleTextualDescription();
    clone.description = new SimpleTextualDescription();
    copyCombo(this, clone);
    return clone;
  }

  @Override
  public void getValuesFrom(ICombo combo) {
    this.clear();
    copyCombo(combo, this);
  }

  private void copyCombo(ICombo source, Combo destination) {
    for (ICharm charm : source.getCreationCharms()) {
      destination.addCharm(charm, false);
    }
    for (ICharm charm : source.getExperiencedCharms()) {
      destination.addCharm(charm, true);
    }
    if (source.getId() != null) {
      destination.setId(source.getId());
    }
    destination.name.setText(source.getName().getText());
    destination.description.setText(source.getDescription().getText());
  }

  @Override
  public void clear() {
    id = null;
    name.setText(""); //$NON-NLS-1$
    description.setText(""); //$NON-NLS-1$
    removeCharms(creationCharmList.toArray(new ICharm[creationCharmList.size()]));
    removeCharms(experiencedCharmList.toArray(new ICharm[experiencedCharmList.size()]));
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }

  @Override
  public ITextualDescription getDescription() {
    return description;
  }

  @Override
  public boolean contains(ICharm charm) {
    return creationCharmList.contains(charm) || experiencedCharmList.contains(charm);
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    Ensure.ensureNotNull("Id must not be null.", id); //$NON-NLS-1$
    this.id = id;
  }
}