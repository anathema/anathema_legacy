package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.text.Text;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.NullObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDescriptionPanel implements EquipmentDescriptionPanel {

  private MigPane pane;

  public FxEquipmentDescriptionPanel() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane = new MigPane();
      }
    });
  }

  @Override
  public ITextView addNameView(String label) {
    final FxTextView view = FxTextView.SingleLine(label);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(view.getNode());
      }
    });
    return view;
  }

  @Override
  public ITextView addDescriptionView(String label) {
    final FxTextView view = FxTextView.MultiLine(label);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(view.getNode());
      }
    });
    return view;
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

  public Node getNode() {
    return pane;
  }

  public void setTitle(final String title) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(new Text(title));
        pane.add(new Separator());
      }
    });
  }
}