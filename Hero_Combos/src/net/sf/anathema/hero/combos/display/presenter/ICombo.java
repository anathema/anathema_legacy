package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICombo extends Cloneable {

  void addComboModelListener(ChangeListener listener);

  void removeCharms(Charm[] charm);

  ICombo clone();

  void clear();

  ITextualDescription getName();

  ITextualDescription getDescription();

  Charm[] getCharms();

  Charm[] getCreationCharms();

  Charm[] getExperiencedCharms();

  boolean contains(Charm charm);

  Integer getId();

  void setId(Integer id);

  void getValuesFrom(ICombo combo);

  void addCharm(Charm charm, boolean experienced);
}