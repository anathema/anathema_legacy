package net.sf.anathema.hero.languages.display;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.library.overview.IOverviewCategory;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxLanguagesView implements LanguagesView, NodeHolder {

  private final MigPane selectionPanel = new MigPane(withoutInsets().fillX());
  private final MigPane entryPanel = new MigPane(withoutInsets().wrapAfter(2).fillX());
  private final MigPane mainPanel = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane overviewPanel = new MigPane(withoutInsets());
  private final MigPane panel = new MigPane(withoutInsets());

  public FxLanguagesView() {
    mainPanel.add(selectionPanel);
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(entryPanel);
    mainPanel.add(scrollPane, new CC().grow().pushY());
    panel.add(mainPanel, new CC().grow().pushY());
    panel.add(overviewPanel, new CC().alignY("top"));
  }

  @Override
  public Node getNode() {
    return panel;
  }

  @Override
  public IButtonControlledObjectSelectionView<Object> addSelectionView(String labelText, AgnosticUIConfiguration renderer, RelativePath addIcon) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public IOverviewCategory addOverview(String border) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public IRemovableEntryView addEntryView(RelativePath removeIcon, Trait trait, String string) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void removeEntryView(IRemovableEntryView removableView) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
