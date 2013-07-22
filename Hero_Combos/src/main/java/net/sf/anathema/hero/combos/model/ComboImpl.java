package net.sf.anathema.hero.combos.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComboImpl implements Combo {

  private volatile Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private volatile List<Charm> creationCharmList = new ArrayList<>();
  private volatile List<Charm> experiencedCharmList = new ArrayList<>();
  private Charm extraActionCharm = null;
  private Charm simpleCharm = null;
  private volatile ITextualDescription name = new SimpleTextualDescription();
  private volatile ITextualDescription description = new SimpleTextualDescription();
  private Integer id = null;

  @Override
  public Charm[] getCharms() {
    ArrayList<Charm> charms = new ArrayList<>();
    charms.addAll(creationCharmList);
    charms.addAll(experiencedCharmList);
    return charms.toArray(new Charm[charms.size()]);
  }

  @Override
  public Charm[] getCreationCharms() {
    return creationCharmList.toArray(new Charm[creationCharmList.size()]);
  }

  @Override
  public Charm[] getExperiencedCharms() {
    return experiencedCharmList.toArray(new Charm[experiencedCharmList.size()]);
  }

  @Override
  public void addCharm(Charm charm, boolean experienced) {
    List<Charm> targetList = experienced ? experiencedCharmList : creationCharmList;
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
  public void removeCharms(Charm[] charms) {
    List<Charm> removal = Arrays.asList(charms);
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
  public ComboImpl clone() {
    ComboImpl clone;
    try {
      clone = (ComboImpl) super.clone();
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
  public void getValuesFrom(Combo combo) {
    this.clear();
    copyCombo(combo, this);
  }

  private void copyCombo(Combo source, ComboImpl destination) {
    for (Charm charm : source.getCreationCharms()) {
      destination.addCharm(charm, false);
    }
    for (Charm charm : source.getExperiencedCharms()) {
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
    removeCharms(creationCharmList.toArray(new Charm[creationCharmList.size()]));
    removeCharms(experiencedCharmList.toArray(new Charm[experiencedCharmList.size()]));
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
  public boolean contains(Charm charm) {
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