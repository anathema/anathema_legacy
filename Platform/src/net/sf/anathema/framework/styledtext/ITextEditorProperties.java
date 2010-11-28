package net.sf.anathema.framework.styledtext;

import javax.swing.Action;

public interface ITextEditorProperties {

  public void initBoldAction(Action action);

  public void initItalicAction(Action action);

  public void initUnderlineAction(Action action);
}