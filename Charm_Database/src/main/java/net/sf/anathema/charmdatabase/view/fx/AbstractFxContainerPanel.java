package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.StyledTitledPane;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;
import org.tbee.javafx.scene.layout.MigPane;

public abstract class AbstractFxContainerPanel {

  protected final MigPane pane;
  private final SelectionViewFactory selectionViewFactory;

  protected AbstractFxContainerPanel(SelectionViewFactory selectionViewFactory, final LC layout, final AC col,
                                     final AC row) {
    this.pane = new MigPane(layout, col, row);
    this.selectionViewFactory = selectionViewFactory;
  }

  protected AbstractFxContainerPanel(final LC layout, final AC col, final AC row) {
    this(null, layout, col, row);
  }

  protected ITextView addSingleTextView(final String label, CC cc) {
    return addTextView(FxTextView.SingleLine(label), cc);
  }

  protected ITextView addTextBoxView(final String label, CC cc) {
    return addTextView(FxTextView.MultiLine(label), cc);
  }

  private ITextView addTextView(final FxTextView view, final CC cc) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(view.getNode(), cc);
      }
    });
    return view;
  }

  public <T> ObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> ui,
                                                     final CC constraints) {
    // TODO: Throw exception if no factory is installed
    final FxObjectSelectionView<T> selectionView = selectionViewFactory.create(label, ui);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane.add(selectionView.getNode(), constraints);
      }
    });
    return selectionView;
  }

  public <T extends NodeHolder> T addSubpanel(final T subpanel, final String title, final CC constraints) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Node titledPane = StyledTitledPane.Create(title, subpanel.getNode());
        pane.add(titledPane, constraints);
      }
    });
    return subpanel;
  }

  public final Node getNode() {
    return pane;
  }
}
