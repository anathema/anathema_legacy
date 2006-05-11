package net.sf.anathema.charmentry.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.charmentry.presenter.view.IPrerequisitesEntryView;
import net.sf.anathema.charmentry.presenter.view.ISelectableTraitView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IIntValueView;

public class PrerequisiteEntryView implements IPrerequisitesEntryView {

  private final IIntValueDisplayFactory factory;
  private final JPanel content = new JPanel(new GridDialogLayout(3, false));

  public PrerequisiteEntryView(IIntValueDisplayFactory factory) {
    this.factory = factory;
  }

  public ISelectableTraitView addSelectablePrerequisiteView(String string, Object[] traits) {
    content.add(new JLabel(string));
    SelectableTraitView view = new SelectableTraitView(factory);
    view.setSelectableTraits(traits);
    view.addTo(content);
    return view;
  }

  public IIntValueView addEssencePrerequisiteView(String label, int minimum, int maximum) {
    content.add(new JLabel(label));
    content.add(new JLabel());
    IIntValueDisplay display = factory.createIntValueDisplay(maximum, minimum);
    content.add(display.getComponent());
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