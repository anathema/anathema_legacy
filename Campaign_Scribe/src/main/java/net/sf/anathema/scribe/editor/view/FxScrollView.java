package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class FxScrollView {

  private MigPane content = new MigPane(new LC().insets("0").gridGap("2", "0").wrapAfter(2), new AC().grow().fill(), new AC().grow().fill());
  public final FxScrollEditor scrollEditor = new FxScrollEditor();
  public final ThreadedFxScrollPreview scrollPreview = new ThreadedFxScrollPreview();

  public FxScrollView() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        initGui();
      }
    });
  }

  private void initGui() {
    content.add(scrollEditor.getNode(), new CC().width("50%"));
    content.add(scrollPreview.getNode(), new CC().width("50%"));
  }

  public Node getNode() {
    return content;
  }
}
