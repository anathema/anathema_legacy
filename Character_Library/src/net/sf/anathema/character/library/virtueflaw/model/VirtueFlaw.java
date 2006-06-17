package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class VirtueFlaw implements IVirtueFlaw {

  private ITraitType root;
  private final ITextualDescription name = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final ChangeControl control = new ChangeControl();

  public ITraitType getRoot() {
    return root;
  }

  public void setRoot(ITraitType root) {
    this.root = root;
    control.fireChangedEvent();
  }

  public void addRootChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public ITextualDescription getName() {
    return name;
  }

  public boolean isFlawComplete() {
    return !(root == null && name.isEmpty());
  }
}