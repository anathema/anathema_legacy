package net.sf.anathema.character.impl.view;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.view.basic.ButtonControlledComboEditView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.trait.view.FrontToggleButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.GroupedGridDialogPanel;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class GroupedFavorableTraitConfigurationView extends AbstractTabView<Object> implements
    IGroupedFavorableTraitConfigurationView {

  private GroupedGridDialogPanel groupedTraitView;
  private GridDialogPanel specialtyDialogPanel;
  private JPanel specialtyPanel;
  private final IIntValueDisplayFactory markerIntValueDisplayFactory;
  private final IIntValueDisplayFactory markerLessIntValueDisplayFactory;

  public GroupedFavorableTraitConfigurationView(
      int columnCount,
      String header,
      IIntValueDisplayFactory factoryWithMarker,
      IIntValueDisplayFactory factoryWithoutMarker) {
    super(header);
    this.groupedTraitView = new GroupedGridDialogPanel(columnCount);
    this.markerIntValueDisplayFactory = factoryWithMarker;
    this.markerLessIntValueDisplayFactory = factoryWithoutMarker;
  }

  public ISpecialtyView addSpecialtyView(
      String abilityName,
      String specialtyName,
      Icon deleteIcon,
      int value,
      int maxValue) {
    initSpecialtyPanels();
    SpecialtyView specialtyView = new SpecialtyView(
        markerIntValueDisplayFactory,
        abilityName,
        deleteIcon,
        specialtyName,
        value,
        maxValue);
    specialtyView.addComponents(specialtyPanel);
    return specialtyView;
  }

  public IToggleButtonTraitView<SimpleTraitView> addTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties) {
    return createTraitView(labelText, value, maxValue, selected, properties, markerIntValueDisplayFactory);
  }

  public IToggleButtonTraitView< ? > addMarkerLessTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties) {
    return createTraitView(labelText, value, maxValue, selected, properties, markerLessIntValueDisplayFactory);
  }

  private IToggleButtonTraitView<SimpleTraitView> createTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties,
      IIntValueDisplayFactory factory) {
    SimpleTraitView view = new SimpleTraitView(factory, labelText, value, maxValue);
    FrontToggleButtonTraitViewWrapper<SimpleTraitView> abilityView = new FrontToggleButtonTraitViewWrapper<SimpleTraitView>(
        view,
        properties,
        selected);
    groupedTraitView.addEntry(abilityView);
    return abilityView;
  }

  @Override
  protected void createContent(JPanel component, Object object) {
    if (specialtyPanel != null) {
      groupedTraitView.startNewGroup(null);
      groupedTraitView.addEntry(createSpecialtyDialogComponent());
    }
    groupedTraitView.addOverallView(component);
  }

  private IGridDialogPanelContent createSpecialtyDialogComponent() {
    return new IGridDialogPanelContent() {
      public void addComponents(IGridDialogPanel abilityPanel) {
        abilityPanel.add(new IDialogComponent() {
          public int getColumnCount() {
            return 1;
          }

          public void fillInto(JPanel panel, int columnCount) {
            GridDialogLayoutData data = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
            data.setHorizontalSpan(columnCount);
            panel.add(specialtyDialogPanel.getContent(), data);
            panel.add(specialtyPanel, data);
          }
        });
      }
    };
  }

  public void startNewTraitGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }

  public IButtonControlledComboEditView<ITraitType> addSpecialtySelectionView(
      String labelText,
      ITraitType[] traitTypes,
      ListCellRenderer renderer,
      Icon addIcon) {
    initSpecialtyPanels();
    ButtonControlledComboEditView<ITraitType> objectSelectionView = new ButtonControlledComboEditView<ITraitType>(
        traitTypes,
        addIcon);
    objectSelectionView.addTo(specialtyDialogPanel, labelText, renderer);
    return objectSelectionView;
  }

  private void initSpecialtyPanels() {
    if (specialtyPanel == null) {
      specialtyPanel = new JPanel(new GridDialogLayout(5, false));
      specialtyDialogPanel = new GridDialogPanel(false);
    }
  }
}