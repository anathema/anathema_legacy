package net.sf.anathema.fx.hero.creation;

import javafx.scene.control.ScrollPane;
import javafx.stage.Window;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.creation.CharacterCreationView;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import org.controlsfx.dialog.Dialog;
import org.tbee.javafx.scene.layout.MigPane;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;

public class FxCharacterCreationView implements CharacterCreationView {

  public static final String NO_TITLE = "";
  private final MigPane component = new MigPane(new LC().gridGapX("10").gridGapY("10").wrapAfter(2));
  private final Dialog dialog;

  public FxCharacterCreationView(Window parent) {
    this.dialog = new Dialog(parent, NO_TITLE, false, true);
  }

  @Override
  public ToggleButtonPanel addToggleButtonPanel() {
    FxToggleButtonPanel panel = new FxToggleButtonPanel();
    component.add(panel.getNode(), new CC().grow().pushY());
    return panel;
  }

  @Override
  public VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList() {
    ListSelectionView<HeroTemplate> view = new ListSelectionView<>();
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setHbarPolicy(NEVER);
    scrollPane.setVbarPolicy(AS_NEEDED);
    scrollPane.setContent(view.getNode());
    component.add(scrollPane, new CC().grow().push());
    return view;
  }

  @Override
  public void show() {
    dialog.setContent(component);
    dialog.show();
  }

  @Override
  public void close() {
    dialog.hide();
  }

  @Override
  public Tool addButton() {
    ControlsFxTool tool = new ControlsFxTool();
    dialog.getActions().add(tool.getAction());
    return tool;
  }

  @Override
  public void setTitle(String title) {
    dialog.setTitle(title);
  }
}