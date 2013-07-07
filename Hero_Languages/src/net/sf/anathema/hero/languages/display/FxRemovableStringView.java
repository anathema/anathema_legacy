package net.sf.anathema.hero.languages.display;

import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxRemovableStringView implements RemovableEntryView {
  private final Label label;
  private final FxButtonTool button;
  private MigPane parent;

  public FxRemovableStringView(RelativePath removeIcon, String label) {
    this.label = new Label(label);
    this.button = FxButtonTool.ForToolbar();
    button.setIcon(removeIcon);
  }

  public void addTo(MigPane entryPanel) {
    this.parent = entryPanel;
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        parent.add(label, new CC().growX().pushX().alignY("top"));
        parent.add(button.getNode(), new CC().alignY("top").alignX("right"));
      }
    });
  }

  @Override
  public void addButtonListener(Command command) {
    button.setCommand(command);
  }

  @Override
  public void delete() {
    parent.getChildren().remove(label);
    parent.getChildren().remove(button.getNode());
  }
}
