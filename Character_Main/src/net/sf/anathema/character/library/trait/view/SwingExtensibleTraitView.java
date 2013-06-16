package net.sf.anathema.character.library.trait.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;
import net.sf.anathema.swing.interaction.ToggleActionInteraction;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SwingExtensibleTraitView implements ExtensibleTraitView {
  private final JPanel front = new JPanel();
  private final JPanel center = new JPanel();
  private final JPanel rear = new JPanel();
  private SimpleTraitView view;

  public SwingExtensibleTraitView(SimpleTraitView view) {
    this.view = view;
    addInnerView(center);
  }

  @Override
  public IIntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleButtonInFront() {
    ToggleActionInteraction toggleActionInteraction = new ToggleActionInteraction();
    JComponent button = toggleActionInteraction.getButton();
    front.add(button, SwingLayoutUtils.constraintsForImageButton(button).gapAfter("5"));
    return toggleActionInteraction;
  }

  public void addComponents(JPanel currentColumn) {
    currentColumn.add(front);
    currentColumn.add(center);
    currentColumn.add(rear);
  }

  protected void addInnerView(JPanel panel) {
    JPanel innerViewPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
    view.addComponents(innerViewPanel);
    panel.add(innerViewPanel, new CC().growX().pushX());
  }
}