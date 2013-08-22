package net.sf.anathema.hero.spiritual.display;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.fx.hero.traitview.FxTraitView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.StyledTitledPane;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxSpiritualTraitsView implements SpiritualTraitsView, NodeHolder {

  private final MigPane virtuePanel = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final MigPane willpowerPanel = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final FxEssenceView essenceView = new FxEssenceView();
  private final MigPane content = new MigPane(withoutInsets().wrapAfter(2));

  @Override
  public void initGui(SpiritualTraitsViewProperties properties) {
    addTitledPanel(properties.getVirtueTitle(), content, virtuePanel, new CC().spanY().growY().alignY("top"));
    addTitledPanel(properties.getWillpowerTitle(), content, willpowerPanel, new CC().alignY("top").growX());
    addTitledPanel(properties.getEssenceTitle(), content, essenceView.getNode(), new CC().alignY("top"));
  }

  @Override
  public IntValueView addVirtue(String labelText, int maxValue) {
    FxTraitView willpowerView = FxTraitView.WithDefaultLayout(labelText, maxValue);
    willpowerView.addTo(virtuePanel);
    return willpowerView;
  }

  @Override
  public IntValueView addWillpower(String labelText, int maxValue) {
    FxTraitView willpowerView = FxTraitView.WithDefaultLayout(labelText, maxValue);
    willpowerView.addTo(willpowerPanel);
    return willpowerView;
  }

  @Override
  public IntValueView addEssenceView(String labelText, int maxValue) {
    return essenceView.addEssenceView(labelText, maxValue);
  }

  @Override
  public IValueView<String> addPoolView(String labelText) {
    return essenceView.addPoolView(labelText);
  }

  @Override
  public Node getNode() {
    return content;
  }

  private void addTitledPanel(String title, MigPane parent, Node content, CC constraints) {
    Node pane = StyledTitledPane.Create(title, content);
    parent.add(pane, constraints);
  }
}
