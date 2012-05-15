package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

public interface ICheckableTextView {

  ITextView getTextView();

  IBooleanValueView getBooleanValueView();
}