package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IVirtueFlaw {
  public VirtueType getRoot();

  public void setRoot(VirtueType root);

  public ITextualDescription getName();

  public boolean isFlawComplete();

  public void addRootChangeListener(IChangeListener listener);
}