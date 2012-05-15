package net.sf.anathema.framework.itemdata.view;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.text.StyledDocument;
import java.awt.Dimension;

public interface IBasicItemDescriptionView extends IInitializableContentView<Object> {

  ITextView addLineTextView(String labelName);

  IStyledTextView addStyledTextView(String labelName, StyledDocument document, Dimension preferredSize, ITextEditorProperties properties);
}