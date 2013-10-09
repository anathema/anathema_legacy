package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.presenter.DirectUi;
import net.sf.anathema.charmdatabase.presenter.TypeUi;
import net.sf.anathema.charmdatabase.view.CharmBasicsPanel;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmBasicsPanel implements CharmBasicsPanel {

  private final SelectionViewFactory selectionViewFactory;
  private MigPane pane;

  public FxCharmBasicsPanel(SelectionViewFactory selectionFactory) {
    this.selectionViewFactory = selectionFactory;
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane = new MigPane(new LC().wrapAfter(2).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
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

  public Node getNode() {
	  return pane;
  }

  @Override
  public ObjectSelectionView<Identifier> addTypeView(String label,
		  DirectUi ui) {
	  final FxObjectSelectionView<Identifier> selectionView = selectionViewFactory.create(label, ui);
	  Platform.runLater(new Runnable() {
		  @Override
		  public void run() {
			  pane.add(selectionView.getNode(), new CC().grow());
		  }
	  });
	  return selectionView;
  }

  @Override
  public ObjectSelectionView<Identifier> addGroupView(String label,
		  TypeUi ui) {
	  final FxObjectSelectionView<Identifier> selectionView = selectionViewFactory.create(label, ui);
	  Platform.runLater(new Runnable() {
		  @Override
		  public void run() {
			  pane.add(selectionView.getNode(), new CC().grow());
		  }
	  });
	  return selectionView;
  }
}