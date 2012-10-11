package net.sf.anathema.campaign.note.view;

import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IBasicItemDescriptionView {

  ITextView addLineTextView(String labelName);

  IStyledTextView addStyledTextView(String labelName);
}