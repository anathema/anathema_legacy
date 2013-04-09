package net.sf.anathema.character.equipment.item.view.fx;

import javafx.scene.Node;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.view.NullTextView;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.NullObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDetails implements EquipmentDetails {
  public Node getNode() {
    return new MigPane();
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          TechnologyAgnosticUIConfiguration<IEquipmentStats> configuration) {
    return new NullToolListView();
  }

  @Override
  public void setStatsListHeader(String headerText) {
    //nothing to do
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    return new EquipmentDescriptionPanel() {
      @Override
      public ITextView addNameView(String label) {
        return new NullTextView();
      }

      @Override
      public ITextView addDescriptionView(String label) {
        return new NullTextView();
      }

      @Override
      public IObjectSelectionView<MaterialComposition> addCompositionView(String label,
                                                                          TechnologyAgnosticUIConfiguration<MaterialComposition> ui) {
        return new NullObjectSelectionView<>();
      }

      @Override
      public IObjectSelectionView<MagicalMaterial> addMaterialView(String label,
                                                                   TechnologyAgnosticUIConfiguration<MagicalMaterial> ui) {
        return new NullObjectSelectionView<>();
      }

      @Override
      public CostSelectionView addCostView(String label) {
        return new NullCostSelectionView();
      }
    };
  }
}
