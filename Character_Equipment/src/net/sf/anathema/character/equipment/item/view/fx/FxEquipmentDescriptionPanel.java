package net.sf.anathema.character.equipment.item.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDescriptionPanel implements EquipmentDescriptionPanel {

  private final SelectionViewFactory selectionViewFactory;
  private MigPane pane;

  public FxEquipmentDescriptionPanel(SelectionViewFactory selectionFactory) {
    this.selectionViewFactory = selectionFactory;
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane = new MigPane(new LC().wrapAfter(1).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
      }
    });
  }

  @Override
  public ITextView addNameView(String label) {
    final FxTextView view = FxTextView.SingleLine(label);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(view.getNode(), new CC().growX().pushY().span());
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
        pane.add(view.getNode(), new CC().growX().pushY().span());
      }
    });
    return view;
  }

  @Override
  public IObjectSelectionView<MaterialComposition> addCompositionView(String label,
                                                                      AgnosticUIConfiguration<MaterialComposition> ui) {
    final FxObjectSelectionView<MaterialComposition> selectionView = selectionViewFactory.create(label, ui);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(selectionView.getNode(), new CC().split());
      }
    });
    return selectionView;
  }

  @Override
  public IObjectSelectionView<MagicalMaterial> addMaterialView(String label,
                                                               AgnosticUIConfiguration<MagicalMaterial> ui) {
    final FxObjectSelectionView<MagicalMaterial> selectionView = selectionViewFactory.create(label, ui);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(selectionView.getNode(), new CC().growX().wrap());
      }
    });
    return selectionView;
  }

  @Override
  public CostSelectionView addCostView(String label) {
    final FxCostSelectionView costSelectionView = new FxCostSelectionView(label, selectionViewFactory);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(costSelectionView.getNode(), new CC().split(2).pushX());
      }
    });
    return costSelectionView;
  }

  public Node getNode() {
    return pane;
  }
}