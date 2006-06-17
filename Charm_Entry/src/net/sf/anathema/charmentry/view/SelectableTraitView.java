package net.sf.anathema.charmentry.view;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.charmentry.presenter.ITraitSelectionChangedListener;
import net.sf.anathema.charmentry.presenter.view.ISelectableTraitView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class SelectableTraitView implements ISelectableTraitView {

  private IChangeableJComboBox<IIdentificate> traitSelectionBox = new ChangeableJComboBox<IIdentificate>(
      new IIdentificate[] { new Identificate("Select") },
      false);

  private final IIntValueDisplay valueDisplay;
  private final GenericControl<ITraitSelectionChangedListener> control = new GenericControl<ITraitSelectionChangedListener>();
  private int currentValue;

  public SelectableTraitView(IIntValueDisplayFactory configuration) {
    this.valueDisplay = configuration.createIntValueDisplay(EssenceTemplate.SYSTEM_ESSENCE_MAX, 1);
    traitSelectionBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        fireTraitChangedEvent();
      }
    });
    valueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        currentValue = newValue;
        fireTraitChangedEvent();
      }
    });
  }

  private void fireTraitChangedEvent() {
    control.forAllDo(new IClosure<ITraitSelectionChangedListener>() {
      public void execute(ITraitSelectionChangedListener input) {
        input.selectionChanged(traitSelectionBox.getSelectedObject(), currentValue);
      }
    });
  }

  public JComponent getContent() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    addTo(panel);
    return panel;
  }

  public void addTo(JPanel panel) {
    panel.add(traitSelectionBox.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(valueDisplay.getComponent());
    panel.revalidate();
  }

  public void setSelectableTraits(IIdentificate[] traits) {
    traitSelectionBox.setObjects(traits);
  }

  public void addTraitSelectionListener(ITraitSelectionChangedListener listener) {
    control.addListener(listener);
  }

  public void setSelectedTrait(IIdentificate object) {
    traitSelectionBox.setSelectedObject(object);
  }

  public void setValue(int value) {
    valueDisplay.setValue(value);
  }

  public JComponent getSelectionComponent() {
    return traitSelectionBox.getComponent();
  }

  public Component getValueComponent() {
    return valueDisplay.getComponent();
  }
}