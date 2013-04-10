package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.lib.gui.ConfigurableSwingUI;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.framework.swing.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.workflow.container.factory.MigPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;

public class SwingEquipmentDescriptionPanel implements EquipmentDescriptionPanel {

  private final MigPanelBuilder panelBuilder = new MigPanelBuilder();
  private final String panelTitle;

  public SwingEquipmentDescriptionPanel(String title) {
    this.panelTitle = title;
  }

  public JComponent getContent() {
    return panelBuilder.getTitledContent(panelTitle);
  }

  @Override
  public ITextView addNameView(String label) {
    return panelBuilder.addLineTextView(label);
  }

  @Override
  public ITextView addDescriptionView(String label) {
    return panelBuilder.addAreaTextView(label, 5);
  }

  @Override
  public IObjectSelectionView<MaterialComposition> addCompositionView(String label, AgnosticUIConfiguration<MaterialComposition> ui) {
    ObjectUiListCellRenderer renderer = new ObjectUiListCellRenderer(new ConfigurableSwingUI<>(ui));
    ObjectSelectionView<MaterialComposition> compositionView = new ObjectSelectionView<>(label, renderer);
    panelBuilder.addView(compositionView, new CC().split(3).gapAfter("15"));
    return compositionView;
  }

  @Override
  public IObjectSelectionView<MagicalMaterial> addMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> ui) {
    ObjectUiListCellRenderer renderer = new ObjectUiListCellRenderer(new ConfigurableSwingUI<>(ui));
    ObjectSelectionView<MagicalMaterial> materialView = new ObjectSelectionView<>(label, renderer);
    panelBuilder.addView(materialView, new CC());
    return materialView;
  }

  @Override
  public CostSelectionView addCostView(String label) {
    MarkerIntValueDisplayFactory displayFactory = new MarkerIntValueDisplayFactory(new EquipmentIntValueGraphics());
    SwingCostSelectionView costView = new SwingCostSelectionView(label, displayFactory);
    panelBuilder.addView(costView, new CC().split(2).pushX());
    return costView;
  }
}
