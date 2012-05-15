package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.library.quality.presenter.IQuality;

public interface IMutation extends IQuality {

  void accept(IMutationVisitor visitor);

  IExaltedSourceBook getSource();

  Integer getPage();

  int getCost();
}
