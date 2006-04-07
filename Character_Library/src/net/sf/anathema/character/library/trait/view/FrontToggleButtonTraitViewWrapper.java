package net.sf.anathema.character.library.trait.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IFavorableIntValueView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;

public class FrontToggleButtonTraitViewWrapper extends AbstractTraitViewWrapper implements
    IFavorableIntValueView,
    IGridDialogPanelContent {

  private final IconToggleButton button;
  private final IIconToggleButtonProperties properties;
  private JPanel traitViewPanel;
  private JPanel innerViewPanel;

  public FrontToggleButtonTraitViewWrapper(ITraitView view, IIconToggleButtonProperties properties, boolean selected) {
    super(view);
    this.properties = properties;
    this.button = new IconToggleButton(properties.createStandardIcon(), properties.createUnselectedIcon());
    setButtonState(selected, true);
  }

  public void addComponents(GridDialogPanel abilityPanel) {
    abilityPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addComponents(panel);
      }
    });
  }

  public void addComponents(JPanel panel) {
    this.traitViewPanel = panel;
    GridDialogLayoutData gridLayoutData = new GridDialogLayoutData();
    gridLayoutData.setHorizontalIndent(5);
    panel.add(button.getComponent(), gridLayoutData);
    this.innerViewPanel = new JPanel(new GridDialogLayout(2, false));
    getView().addComponents(innerViewPanel);
    panel.add(innerViewPanel, GridDialogLayoutData.FILL_HORIZONTAL);
  }

  public void delete() {
    traitViewPanel.remove(button.getComponent());
    getView().delete();
    traitViewPanel.remove(innerViewPanel);
    traitViewPanel.revalidate();
  }

  public void setButtonState(boolean selected, boolean enabled) {
    button.setIconSet(properties.createStandardIcon(), properties.createUnselectedIcon());
    button.setEnabled(enabled);
    button.setSelected(selected);
  }

  public void addButtonSelectedListener(final IBooleanValueChangedListener listener) {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(!button.isSelected());
      }
    });
  }
}