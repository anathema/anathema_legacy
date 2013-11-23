package net.sf.anathema.framework.repository.preferences;

import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.File;
import java.nio.file.Path;

public class RepositoryPreferenceView implements PreferenceView, NodeHolder {

  private final MigPane pane = new MigPane(LayoutUtils.fillWithoutInsets());
  private final Announcer<ObjectValueListener> announcer = Announcer.to(ObjectValueListener.class);

  public ITextView addRepositoryDisplay(String label) {
    FxTextView view = FxTextView.SingleLine(label);
    pane.add(view.getNode());
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }

  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    pane.add(tool.getNode(), new CC().alignX("right"));
    return tool;
  }

  public void selectNewRepository(String prompt) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle(prompt);
    File file = chooser.showDialog(null);
    if (file != null) {
      announcer.announce().valueChanged(file.toPath());
    }
  }

  public void whenRepositoryChangeIsRequested(ObjectValueListener<Path> objectValueListener) {
    announcer.addListener(objectValueListener);
  }
}