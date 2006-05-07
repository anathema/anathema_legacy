package net.sf.anathema.charmentry.demo;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.charmentry.presenter.ISelectableTraitView;
import net.sf.anathema.charmentry.view.SelectableTraitView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IIntValueView;

public class PrerequisiteEntryView implements IPrerequisitesEntryView {

  private final IIntValueDisplayFactory factory;
  private final JComponent content = new JPanel(new GridDialogLayout(5, false));

  public PrerequisiteEntryView(IIntValueDisplayFactory factory) {
    this.factory = factory;
  }

  public ISelectableTraitView addSelectablePrerequisiteView(Object[] traits) {
    SelectableTraitView view = new SelectableTraitView(factory);
    view.setSelectableTraits(traits);
    content.add(view.getContent());
    return view;
  }

  public IIntValueView addPrerequisiteView(String label, String traitLabel, int minimum, int maximum) {
    content.add(new JLabel(label));
    GridDialogLayoutData data = GridDialogLayoutDataUtilities.createHorizontalSpanData(2);
    content.add(new JLabel(traitLabel), data);
    IIntValueDisplay display = factory.createIntValueDisplay(maximum, minimum);
    content.add(display.getComponent(), data);
    return display;
  }

  public JComponent getContent() {
    return content;
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }
}