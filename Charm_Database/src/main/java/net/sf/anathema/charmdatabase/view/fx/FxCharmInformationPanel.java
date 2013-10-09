package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.StyledTitledPane;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmInformationPanel implements CharmInformationPanel {
	
	private MigPane pane;
	private final FxCharmSourcePanel sourcePanel;

	public FxCharmInformationPanel(Resources resources) {
	    Platform.runLater(new Runnable() {
	      @Override
	      public void run() {
	        pane = new MigPane(new LC().wrapAfter(3).fill().insets("4"), new AC(), new AC());
	      }
	    });
	    sourcePanel = new FxCharmSourcePanel(resources);
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
	        pane.add(view.getNode(), new CC().spanX(2).grow());
	      }
	    });
	    return view;
	  }

	@Override
	public CharmSourcePanel addSourcePanel(final String title) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Node titledPane = StyledTitledPane.Create(title, sourcePanel.getNode());
				pane.add(titledPane, new CC().grow());
			}
		});
		return sourcePanel;
	}
}
