package net.sf.anathema.character.library.trait.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;
import net.sf.anathema.swing.interaction.ActionInteraction;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class SwingExtensibleTraitView implements ExtensibleTraitView {
  private final JPanel front = new JPanel(new MigLayout(fillWithoutInsets()));
  private final JPanel center = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private final JPanel rear = new JPanel(new MigLayout(fillWithoutInsets()));
  private final IIntValueView view;

  public SwingExtensibleTraitView(SimpleTraitView view) {
    this.view = view;
    view.addComponents(center);
  }

  @Override
  public IIntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleInFront(IIconToggleButtonProperties properties) {
    return addToggle(properties, front);
  }

  @Override
  public ToggleTool addToggleBehind(IIconToggleButtonProperties properties) {
    return addToggle(properties, rear);
  }

  @Override
  public Tool addToolBehind() {
    ActionInteraction interaction = new ActionInteraction();
    interaction.addTo(new AddToTraitView(rear));
    return interaction;
  }

  @Override
  public void remove() {
    removePart(front);
    removePart(center);
    removePart(rear);
  }

  public void addComponents(JPanel parent) {
    parent.add(front);
    parent.add(center, new CC().growX().pushX());
    parent.add(rear);
  }

  private ToggleTool addToggle(IIconToggleButtonProperties properties, JPanel parent) {
    TraitViewInteraction toggleActionInteraction = new TraitViewInteraction(properties);
    JComponent button = toggleActionInteraction.getButton();
    parent.add(button, SwingLayoutUtils.constraintsForImageButton(button));
    return toggleActionInteraction;
  }

  private void removePart(JPanel panel) {
    panel.getParent().remove(panel);
  }
}