package net.sf.anathema.character.library.trait.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;

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
  public ToggleTool addToggleButtonInFront(IIconToggleButtonProperties properties) {
    TraitViewInteraction toggleActionInteraction = new TraitViewInteraction(properties);
    JComponent button = toggleActionInteraction.getButton();
    front.add(button, SwingLayoutUtils.constraintsForImageButton(button));
    return toggleActionInteraction;
  }

  public void addComponents(JPanel currentColumn) {
    currentColumn.add(front);
    currentColumn.add(center, new CC().growX().pushX());
    currentColumn.add(rear);
  }
}