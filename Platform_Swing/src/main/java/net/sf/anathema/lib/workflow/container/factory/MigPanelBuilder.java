package net.sf.anathema.lib.workflow.container.factory;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MigPanelBuilder {
  private final JPanel panel;

  public MigPanelBuilder(int elementsPerRow) {
    panel = new JPanel(new MigLayout(new LC().wrapAfter(elementsPerRow).fill().insets("2")));
  }

  public ITextView addSpanningLineTextView(String label, int columnCount) {
    LineTextView textView = new LineTextView(columnCount);
    LabelTextView labelTextView = new LabelTextView(label, textView);
    labelTextView.addToMigPanelSpanning(panel);
    return textView;
  }

  public void addComponent(JComponent component, CC constraint) {
    panel.add(component, constraint);
  }

  public void addView(AdditiveView view, CC data) {
    view.addTo(panel, data);
  }

  public JComponent getUntitledContent() {
    return panel;
  }
}