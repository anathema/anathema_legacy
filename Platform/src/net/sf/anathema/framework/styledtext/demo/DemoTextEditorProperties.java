package net.sf.anathema.framework.styledtext.demo;

import javax.swing.Action;

import net.sf.anathema.framework.styledtext.ITextEditorProperties;

public class DemoTextEditorProperties implements ITextEditorProperties {

  public void initBoldAction(Action action) {
    action.putValue(Action.NAME, "B"); //$NON-NLS-1$
  }

  public void initItalicAction(Action action) {
    action.putValue(Action.NAME, "I"); //$NON-NLS-1$
  }

  public void initUnderlineAction(Action action) {
    action.putValue(Action.NAME, "U"); //$NON-NLS-1$
  }
}