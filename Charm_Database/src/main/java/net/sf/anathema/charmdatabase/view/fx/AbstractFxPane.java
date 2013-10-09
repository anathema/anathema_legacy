package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;

import org.tbee.javafx.scene.layout.MigPane;

public abstract class AbstractFxPane {
	protected MigPane pane;
	
	protected AbstractFxPane(final LC layout, final AC col, final AC row) {		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(layout, col, row);
			}
		});
	}
	
	protected void addToPane(final Node node, final CC cc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane.add(node, cc);
			}
		});
	}

	public final Node getNode() {
		return pane;
	}
}
