package net.sf.anathema.campaign.styledtext;

import javax.swing.Action;

public interface ITextEditorProperties {

  void initBoldAction(Action action);

  void initItalicAction(Action action);

  void initUnderlineAction(Action action);
}