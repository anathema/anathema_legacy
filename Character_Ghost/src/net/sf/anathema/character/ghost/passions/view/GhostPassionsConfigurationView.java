package net.sf.anathema.character.ghost.passions.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.swing.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class GhostPassionsConfigurationView implements IGhostPassionsConfigurationView, IView {
  private final IntegerViewFactory factory;
  private final JPanel mainPanel = new JPanel(new MigLayout(fillWithoutInsets()));
  private final JPanel passionListPanel = new JPanel(new MigLayout(withoutInsets().fillX().wrapAfter(5)));
  private final JPanel passionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final JPanel overviewPanel = new JPanel();
  private ButtonControlledComboEditView<ITraitReference> objectSelectionView;

  public GhostPassionsConfigurationView(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public IButtonControlledComboEditView<ITraitReference> addPassionSelectionView(String labelText,
                                                                                 ListCellRenderer renderer,
                                                                                 Icon addIcon) {
    objectSelectionView = new ButtonControlledComboEditView<>(addIcon, renderer);
    passionPanel.add(objectSelectionView.getComponent());
    return objectSelectionView;
  }

  @Override
  public IPassionView addPassionView(String virtueName, String passionName, Icon deleteIcon, int value, int maxValue) {
    PassionView passionView = new PassionView(factory, virtueName, deleteIcon, passionName, value, maxValue);
    passionView.addComponents(passionListPanel);
    return passionView;
  }

  @Override
  public JComponent getComponent() {
    passionPanel.add(new JScrollPane(passionListPanel), new CC().grow().pushY());
    mainPanel.add(passionPanel, new CC().grow().pushY());
    mainPanel.add(overviewPanel, new CC().grow().pushY());
    return mainPanel;
  }

  @Override
  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(overviewPanel, borderLabel, false);
  }

  @Override
  public void setOverview(IOverviewCategory overview) {
    overviewPanel.removeAll();
    overviewPanel.add(overview.getComponent());
  }

  @Override
  public void removeControls() {
    if (objectSelectionView != null) {
      passionPanel.remove(objectSelectionView.getComponent());
    }
    overviewPanel.removeAll();
  }
}