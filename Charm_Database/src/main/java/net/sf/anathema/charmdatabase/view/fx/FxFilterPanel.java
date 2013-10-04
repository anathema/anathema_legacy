package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.view.CharmFilterPanel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxFilterPanel implements CharmFilterPanel {
	//private final SelectionViewFactory selectionViewFactory;
	  private MigPane pane;

	  public FxFilterPanel() {
	    //this.selectionViewFactory = selectionFactory;
	    Platform.runLater(new Runnable() {
	      @Override
	      public void run() {
	        pane = new MigPane(new LC().wrapAfter(1).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
	      }
	    });
	  }

	  @Override
	  public ITextView addTextFilterView(String label) {
	    final FxTextView view = FxTextView.SingleLine(label);
	    Platform.runLater(new Runnable() {
	      @Override
	      public void run() {
	        pane.add(view.getNode(), new CC().growX().pushY().span());
	      }
	    });
	    return view;
	  }
	  
	  public MigPane getNode() {
		  return pane;
	  }
}
