package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.framework.view.util.OptionalView;
import net.sf.anathema.fx.hero.overview.FxOverviewDisplay;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class TaskedCharacterPane implements CharacterPane, FxOverviewDisplay {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final MigPane viewPanel = new MigPane();
  private final MigPane overviewPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final MigPane content = new MigPane(LayoutUtils.fillWithoutInsets());
  private final FxOptionalOverview overview = new FxOptionalOverview(overviewPane);

  public TaskedCharacterPane() {
    content.add(paneContainer, new CC().alignY("top"));
    content.add(viewPanel, new CC().push().grow());
    content.add(overviewPane, new CC().alignX("right").alignY("top"));
  }

  public OptionalView getOverview() {
    return overview;
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewPanel);
  }

  @Override
  public void setOverviewPane(final Node node) {
    overview.setOverview(node);
  }

  public Node getNode() {
    return content;
  }
}