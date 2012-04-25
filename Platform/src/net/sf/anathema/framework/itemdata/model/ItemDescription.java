package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.StyledTextualDescription;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ItemDescription implements IItemDescription {

  private final ITextualDescription name;
  private final IStyledTextualDescription content;
  private final ChangeControl control = new ChangeControl();

  public ItemDescription() {
    this(""); //$NON-NLS-1$
  }

  public ItemDescription(String initialName) {
    this.name = new SimpleTextualDescription(initialName);
    IObjectValueChangedListener<String> listener = new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        control.fireChangedEvent();
      }
    };
    this.content = new StyledTextualDescription();
    name.addTextChangedListener(listener);
    content.addTextChangedListener(listener);
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }

  @Override
  public IStyledTextualDescription getContent() {
    return content;
  }

  @Override
  public void setClean() {
    name.setDirty(false);
    content.setDirty(false);
    control.fireChangedEvent();
  }

  @Override
  public boolean isDirty() {
    return name.isDirty() || content.isDirty();
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    control.removeChangeListener(changeListener);
  }
}