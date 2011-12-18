package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.library.quality.presenter.IQuality;

public interface IMutation extends IQuality {

  public void accept(IMutationVisitor visitor);

  public IExaltedSourceBook getSource();

  public Integer getPage();

  public int getCost();
}
