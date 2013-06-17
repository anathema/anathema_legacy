package net.sf.anathema.character.linguistics.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.removableentry.view.RemovableStringView;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.util.ProxyComboBoxEditor;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class LinguisticsView implements IView, ILinguisticsView {

  private final JPanel selectionPanel = new JPanel(new MigLayout(withoutInsets().fillX()));
  private final JPanel entryPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2).fillX()));
  private final JPanel mainPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final JPanel overviewPanel = new JPanel(new MigLayout(withoutInsets()));
  private final JPanel panel = new JPanel(new MigLayout(withoutInsets()));

  @Override
  public JComponent getComponent() {
    mainPanel.add(selectionPanel);
    mainPanel.add(new JScrollPane(entryPanel), new CC().grow().pushY());
    panel.add(mainPanel, new CC().grow().pushY());
    panel.add(overviewPanel, new CC().alignY("top"));
    return panel;
  }

  @Override
  public IRemovableEntryView addEntryView(RelativePath removeIcon, Trait trait, String string) {
    RemovableStringView view = new RemovableStringView(new ImageProvider().getImageIcon(removeIcon), string);
    view.addContent(entryPanel);
    panel.revalidate();
    return view;
  }

  @Override
  public void removeEntryView(IRemovableEntryView removableView) {
    removableView.delete();
    entryPanel.repaint();
  }

  @SuppressWarnings("unchecked")
  @Override
  public IButtonControlledObjectSelectionView<Object> addSelectionView(String labelText,
                                                                       AgnosticUIConfiguration uiConfiguration,
                                                                       RelativePath addIcon) {
    ListCellRenderer renderer = new ConfigurableListCellRenderer(uiConfiguration);
    ButtonControlledObjectSelectionView<Object> objectSelectionView = new ButtonControlledObjectSelectionView<>(
            renderer, addIcon, labelText);
    objectSelectionView.setEditor(new ProxyComboBoxEditor());
    objectSelectionView.addComponents(selectionPanel);
    return objectSelectionView;
  }

  @Override
  public IOverviewCategory addOverview(String border) {
    return new OverviewCategory(overviewPanel, border, false);
  }
}