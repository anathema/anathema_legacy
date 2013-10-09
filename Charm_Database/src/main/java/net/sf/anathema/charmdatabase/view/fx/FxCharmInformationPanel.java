package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.view.CharmInformationPanel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmInformationPanel implements CharmInformationPanel {
	private MigPane pane;

	public FxCharmInformationPanel() {
	    Platform.runLater(new Runnable() {
	      @Override
	      public void run() {
	        pane = new MigPane(new LC().wrapAfter(1).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
	      }
	    });
	  }
	
	public Node getNode() {
	    return pane;
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
}
