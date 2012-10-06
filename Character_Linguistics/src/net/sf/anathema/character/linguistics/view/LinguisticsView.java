package net.sf.anathema.character.linguistics.view;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

public class LinguisticsView extends AbstractRemovableEntryView<IRemovableEntryView> implements IView, ILinguisticsView {

  private final JPanel selectionPanel = new JPanel(new MigLayout(new LC().fillX().insets("0")));
  private final JPanel entryPanel = new JPanel(new MigLayout(new LC().wrapAfter(2).fillX().insets("0")));
  private final JPanel mainPanel = new JPanel(new MigLayout(new LC().wrapAfter(1).insets("0")));
  private final JPanel overviewPanel = new JPanel(new MigLayout(new LC().insets("0")));
  private final JPanel panel = new JPanel(new MigLayout(new LC().insets("0")));

  @Override
  public JComponent getComponent() {
    mainPanel.add(selectionPanel);
    mainPanel.add(new JScrollPane(entryPanel), new CC().grow().pushY());
    panel.add(mainPanel, new CC().grow().pushY());
    panel.add(overviewPanel, new CC().alignY("top"));
    return panel;
  }

  @Override
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

  @Override
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

  @Override
  public IOverviewCategory addOverview(String border) {
    return new OverviewCategory(overviewPanel, border, false);
  }
}