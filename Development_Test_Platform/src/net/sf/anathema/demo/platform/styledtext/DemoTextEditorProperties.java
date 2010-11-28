package net.sf.anathema.demo.platform.styledtext;

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