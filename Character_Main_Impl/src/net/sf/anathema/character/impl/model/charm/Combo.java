package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Combo implements ICombo {
  
  // Final fields were turned to volatile to allow clone to be implemented

  private volatile ChangeControl control = new ChangeControl();
  private volatile List<ICharm> charmList = new ArrayList<ICharm>();
  private ICharm extraActionCharm = null;
  private ICharm simpleCharm = null;
  private volatile ITextualDescription name = new SimpleTextualDescription();
  private volatile ITextualDescription description = new SimpleTextualDescription();
  private Integer id = null;

  public ICharm[] getCharms() {
    return charmList.toArray(new ICharm[0]);
  }

  public void addCharm(ICharm charm) {
    charmList.add(charm);
    if (charm.getCharmTypeModel().getCharmType() == CharmType.Simple) {
      simpleCharm = charm;
    }
    if (charm.getCharmTypeModel().getCharmType() == CharmType.ExtraAction) {
      extraActionCharm = charm;
    }
    fireComboChanged();
  }

  public void addComboModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  private void fireComboChanged() {
    control.fireChangedEvent();
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
  public Combo clone() {
    Combo clone;
    try {
      clone = (Combo)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.control = new ChangeControl();
    clone.charmList = new ArrayList<ICharm>(charmList.size());
    clone.name = new SimpleTextualDescription();
    clone.description = new SimpleTextualDescription();
    
    copyCombo(this, clone);
    return clone;
  }

  public void getValuesFrom(ICombo combo) {
    this.clear();
    copyCombo(combo, this);
  }

  private void copyCombo(ICombo source, Combo destination) {
    for (ICharm charm : source.getCharms()) {
      destination.addCharm(charm);
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

  public ITextualDescription getName() {
    return name;
  }

  public ITextualDescription getDescription() {
    return description;
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
    this.id = id;
  }
}