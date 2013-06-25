package net.sf.anathema.character.impl.model.charm;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combo implements ICombo {

  private volatile Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private volatile List<ICharm> creationCharmList = new ArrayList<>();
  private volatile List<ICharm> experiencedCharmList = new ArrayList<>();
  private ICharm extraActionCharm = null;
  private ICharm simpleCharm = null;
  private volatile ITextualDescription name = new SimpleTextualDescription();
  private volatile ITextualDescription description = new SimpleTextualDescription();
  private Integer id = null;

  @Override
  public ICharm[] getCharms() {
    ArrayList<ICharm> charms = new ArrayList<>();
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
  public void addComboModelListener(ChangeListener listener) {
    control.addListener(listener);
  }

  private void fireComboChanged() {
    control.announce().changeOccurred();
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

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  @Override
  public Combo clone() {
    Combo clone;
    try {
      clone = (Combo) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.control = Announcer.to(ChangeListener.class);
    clone.creationCharmList = new ArrayList<>(creationCharmList.size());
    clone.experiencedCharmList = new ArrayList<>(experiencedCharmList.size());
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
    name.setText("");
    description.setText("");
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
    Preconditions.checkNotNull(id);
    this.id = id;
  }
}