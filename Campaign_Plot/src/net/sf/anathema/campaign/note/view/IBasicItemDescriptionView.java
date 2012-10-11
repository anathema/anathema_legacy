package net.sf.anathema.campaign.note.view;

import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IBasicItemDescriptionView extends IView {

  ITextView addLineTextView(String labelName);

  IStyledTextView addStyledTextView(String labelName);
}