package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;

public class SimpleTraitContentView implements IContentView {

  private final SimpleTraitView view;

  public SimpleTraitContentView(SimpleTraitView view) {
    this.view = view;
  }

  public JComponent getContent() {
    GridDialogPanel panel = new GridDialogPanel();
    view.addComponents(panel);
    return panel.getContent();
  }
}