package net.sf.anathema.character.linguistics.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.gui.IView;

public class LinguisticsView extends AbstractRemovableEntryView<IRemovableEntryView> implements IView, ILinguisticsView {

  private final JPanel selectionPanel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel entryPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel overviewPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel panel = new JPanel(new GridDialogLayout(2, false));

  public JComponent getComponent() {
    mainPanel.add(selectionPanel);
    GridDialogLayoutData entryData = GridDialogLayoutDataFactory.createHorizontalFillNoGrab();
    entryData.setVerticalAlignment(GridAlignment.FILL);
    entryData.setGrabExcessVerticalSpace(true);
    mainPanel.add(new JScrollPane(entryPanel), entryData);
    panel.add(mainPanel, entryData);
    panel.add(overviewPanel, GridDialogLayoutDataFactory.createTopData());
    return panel;
  }

  public IRemovableEntryView addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string) {
    RemovableStringView view = new RemovableStringView(removeIcon, string);
    view.addContent(entryPanel);
    panel.revalidate();
    return view;
  }

  @Override
  public void removeEntryView(IRemovableEntryView removableView) {
    super.removeEntryView(removableView);
    entryPanel.repaint();
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

  public IOverviewCategory addOverview(String border) {
    return new OverviewCategory(overviewPanel, border, false);
  }
}