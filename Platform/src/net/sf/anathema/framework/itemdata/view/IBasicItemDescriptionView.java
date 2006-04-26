package net.sf.anathema.framework.itemdata.view;

import java.awt.Dimension;

import javax.swing.text.StyledDocument;

import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.framework.styledtext.IStyledTextView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IBasicItemDescriptionView extends ITabView<Object> {

  public ITextView addLineTextView(String labelName);

  public IStyledTextView addStyledTextView(
      String labelName,
      StyledDocument document,
      Dimension preferredSize,
      ITextEditorProperties properties);
}