package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICombo extends Cloneable {

  void addComboModelListener(IChangeListener listener);

  void removeCharms(ICharm[] charm);

  ICombo clone();

  void clear();

  ITextualDescription getName();

  ITextualDescription getDescription();

  ICharm[] getCharms();

  ICharm[] getCreationCharms();

  ICharm[] getExperiencedCharms();

  boolean contains(ICharm charm);

  Integer getId();

  void setId(Integer id);

  void getValuesFrom(ICombo combo);

  void addCharm(ICharm charm, boolean experienced);
}