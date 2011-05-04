package net.sf.anathema.charmentry.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.charmentry.presenter.view.IKeywordView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.lib.util.IIdentificate;

public class KeywordView implements IKeywordView {

  private final JPanel contentPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel dataPanel = new JPanel(new GridDialogLayout(5, false));
  private final JPanel entryPanel = new JPanel(new GridDialogLayout(2, false));

  public IButtonControlledObjectSelectionView<IIdentificate> addObjectSelectionView(
      ListCellRenderer renderer,
      String label,
      Icon icon) {
    ButtonControlledObjectSelectionView<IIdentificate> view = new ButtonControlledObjectSelectionView<IIdentificate>(
        renderer,
        icon,
        label);
    JPanel panel = new JPanel(new GridDialogLayout(3, false));
    view.addComponents(panel);
    dataPanel.add(panel);
    return view;
  }

  public IRemovableEntryView addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string) {
    RemovableStringView view = new RemovableStringView(removeIcon, string);
    view.addContent(entryPanel);
    contentPanel.revalidate();
    return view;
  }

  public void removeEntryView(IRemovableEntryView removableView) {
    removableView.delete();
  }

  public JComponent getContent() {
    contentPanel.add(dataPanel);
    contentPanel.add(entryPanel);
    return contentPanel;
  }

  public void dispose() {
    // Nothing to do
  }

  public void requestFocus() {
    // Nothing to do
  }
}