package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IVirtueFlaw {
  public ITraitType getRoot();

  public void setRoot(ITraitType root);

  public ITextualDescription getName();

  public boolean isFlawComplete();

  public void addRootChangeListener(IChangeListener listener);
}