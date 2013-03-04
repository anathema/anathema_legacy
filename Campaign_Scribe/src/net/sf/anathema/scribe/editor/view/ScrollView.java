package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class ScrollView {

  private MigPane content;
  public final ScrollEditor scrollEditor = new ScrollEditor();
  public final ScrollPreview scrollPreview = new ScrollPreview();

  public ScrollView() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        initGui();
      }
    });
  }

  private void initGui() {
    content = new MigPane(new LC().insets("0").gridGap("2", "0").wrapAfter(2), new AC().grow().fill(), new AC().grow().fill());
    content.add(scrollEditor.getNode(), new CC().width("50%"));
    content.add(scrollPreview.getNode(), new CC().width("50%"));
  }

  public Node getNode() {
    return content;
  }
}
