package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.model.ITextualDescription;

public interface ISimpleTextualDescription extends ITextualDescription {

  public void setText(String text);

  public String getText();

  public void addTextChangedListener(IObjectValueChangedListener<String> listener);

  public boolean isEmpty();
}