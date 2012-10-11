package net.sf.anathema.campaign.note.view;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.text.StyledDocument;

public interface IBasicItemDescriptionView extends IInitializableContentView<Object> {

  ITextView addLineTextView(String labelName);

  IStyledTextView addStyledTextView(String labelName, StyledDocument document, ITextEditorProperties properties);
}