package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;

import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class SimpleTraitContentView implements IContentView {

  private final SimpleTraitView view;

  public SimpleTraitContentView(SimpleTraitView view) {
    this.view = view;
  }

  public JComponent getContent() {
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    view.addComponents(panel);
    return panel.getContent();
  }
}