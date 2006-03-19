package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public interface ICombo extends Cloneable {

  public void addCharm(ICharm charm);

  public void addComboModelListener(IComboModelListener listener);

  public ICharm[] getComboLegalCharms(ICharm[] learnedCharms);

  public void removeCharms(ICharm[] charm);

  public ICombo clone();

  public void clear();

  public ISimpleTextualDescription getName();

  public ISimpleTextualDescription getDescription();

  public ICharm[] getCharms();

  public boolean isComboLegal(ICharm charm);

  public boolean contains(ICharm charm);

  public Integer getId();

  public void setId(Integer id);

  public void getValuesFrom(ICombo combo);
}