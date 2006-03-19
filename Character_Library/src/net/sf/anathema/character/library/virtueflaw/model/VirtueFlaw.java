package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class VirtueFlaw implements IVirtueFlaw {

  private VirtueType root;
  private final ISimpleTextualDescription name = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final ObjectValueControl<VirtueType> control = new ObjectValueControl<VirtueType>();

  public VirtueType getRoot() {
    return root;
  }

  public void setRoot(VirtueType root) {
    VirtueType rootCopy = this.root;
    this.root = root;
    control.fireValueChangedEvent(rootCopy, root);
  }

  public void addRootListener(IObjectValueChangedListener listener) {
    control.addObjectValueChangeListener(listener);
  }

  public ISimpleTextualDescription getName() {
    return name;
  }

  public boolean isFlawComplete() {
    return !(root == null && name.isEmpty());
  }
}