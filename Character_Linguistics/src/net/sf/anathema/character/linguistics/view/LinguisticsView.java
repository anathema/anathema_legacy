package net.sf.anathema.character.linguistics.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class LinguisticsView extends AbstractRemovableEntryView<IRemovableEntryView> implements IView, ILinguisticsView {

  private final IGridDialogPanel selectionPanel = new DefaultGridDialogPanel();
  private final JPanel entryPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));

  public JComponent getComponent() {
    panel.add(selectionPanel.getComponent());
    panel.add(entryPanel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    return panel;
  }

  public IRemovableEntryView addEntryView(Icon removeIcon, String string) {
    RemovableStringView view = new RemovableStringView(removeIcon, string);
    view.addContent(entryPanel);
    panel.revalidate();
    return view;
  }

  public IButtonControlledObjectSelectionView<Object> addSelectionView(
      String labelText,
      ITextFieldComboBoxEditor editor,
      ListCellRenderer renderer,
      Icon addIcon) {
    ButtonControlledObjectSelectionView<Object> objectSelectionView = new ButtonControlledObjectSelectionView<Object>(
        renderer,
        addIcon,
        labelText,
        editor);
    objectSelectionView.addComponents(selectionPanel);
    return objectSelectionView;
  }
}