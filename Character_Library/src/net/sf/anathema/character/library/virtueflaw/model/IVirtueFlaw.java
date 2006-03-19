package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public interface IVirtueFlaw {
  public VirtueType getRoot();

  public void setRoot(VirtueType root);

  public void addRootListener(IObjectValueChangedListener<VirtueType> listener);

  public ISimpleTextualDescription getName();

  public boolean isFlawComplete();
}