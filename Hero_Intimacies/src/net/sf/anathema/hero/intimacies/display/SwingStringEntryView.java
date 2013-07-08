package net.sf.anathema.hero.intimacies.display;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import net.sf.anathema.swing.interaction.ActionInteraction;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SwingStringEntryView implements StringEntryView {

  private final LabelTextView labelTextView;
  private final JPanel buttonPanel = new JPanel(new MigLayout(withoutInsets()));
  private final JPanel content = new JPanel(new MigLayout(withoutInsets()));

  public SwingStringEntryView(String labelText) {
    LineTextView lineTextView = new LineTextView(45);
    labelTextView = new LabelTextView(labelText, lineTextView);
    labelTextView.addToMigPanel(content);
    content.add(buttonPanel);
  }

  @Override
  public void addTextChangeListener(ObjectValueListener<String> listener) {
    labelTextView.addTextChangedListener(listener);
  }

  public Tool addTool() {
    ActionInteraction button = new ActionInteraction();
    button.addTo(new AddToTraitView(buttonPanel));
    return button;
  }

  @Override
  public void clear() {
    labelTextView.setText(null);
  }

  public JComponent getComponent() {
    return content;
  }
}