package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICombo extends Cloneable {

  public void addComboModelListener(IChangeListener listener);

  public void removeCharms(ICharm[] charm);

  public ICombo clone();

  public void clear();

  public ITextualDescription getName();

  public ITextualDescription getDescription();

  public ICharm[] getCharms();
  
  public ICharm[] getCreationCharms();
  
  public ICharm[] getExperiencedCharms();

  public boolean contains(ICharm charm);

  public Integer getId();

  public void setId(Integer id);

  public void getValuesFrom(ICombo combo);

  public void addCharm(ICharm charm, boolean experienced);
}