package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.framework.view.util.OptionalView;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.FxThreading;
import org.tbee.javafx.scene.layout.MigPane;

public class TaskedCharacterPane implements CharacterPane, OverviewDisplay {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final MigPane viewPanel = new MigPane();
  private final MigPane overviewPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final MigPane content = new MigPane(LayoutUtils.fillWithoutInsets());

  public TaskedCharacterPane() {
    content.add(paneContainer, new CC().alignY("top"));
    content.add(viewPanel, new CC().push().grow());
    content.add(overviewPane);
  }

  public OptionalView getOverview() {
    return new FxOptionalOverview(overviewPane);
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewPanel);
  }

  @Override
  public void setOverviewPane(final Node node) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        overviewPane.getChildren().clear();
        overviewPane.add(node);
      }
    });
  }

  public Node getNode() {
    return content;
  }
}