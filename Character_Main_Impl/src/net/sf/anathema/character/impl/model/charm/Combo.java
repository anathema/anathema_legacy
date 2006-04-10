package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Combo implements ICombo {

  private final ChangeControl control = new ChangeControl();
  private final List<ICharm> charmList = new ArrayList<ICharm>();
  private ICharm extraActionCharm = null;
  private ICharm simpleCharm = null;
  private final ISimpleTextualDescription name = new SimpleTextualDescription();
  private final ISimpleTextualDescription description = new SimpleTextualDescription();
  private Integer id = null;

  public ICharm[] getCharms() {
    return charmList.toArray(new ICharm[0]);
  }

  public void addCharm(ICharm charm) {
    charmList.add(charm);
    if (charm.getCharmType() == CharmType.Simple) {
      simpleCharm = charm;
    }
    if (charm.getCharmType() == CharmType.ExtraAction) {
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
  public ICombo clone() {
    Combo clone = new Combo();
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

  public ISimpleTextualDescription getName() {
    return name;
  }

  public ISimpleTextualDescription getDescription() {
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