package net.sf.anathema.hero.concept.display.description.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.concept.display.description.presenter.ICharacterDescriptionView;
import net.sf.anathema.hero.concept.display.description.presenter.IMultiComponentLine;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxDescriptionView implements ICharacterDescriptionView, NodeHolder {
  private MigPane pane = new MigPane(withoutInsets().wrapAfter(2));
  private final List<MigPane> buttonPanels = new ArrayList<>();

  @Override
  public IMultiComponentLine addMultiComponentLine() {
    final FxMultiComponentLine line = new FxMultiComponentLine();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        pane.add(line.getNode(), new CC().spanX().wrap());
      }
    });
    return line;
  }

  @Override
  public ITextView addLineView(String labelText) {
    FxTextView view = FxTextView.SingleLine(labelText);
    addTextView(view);
    return view;
  }

  @Override
  public ITextView addAreaView(String labelText) {
    FxTextView view = FxTextView.MultiLine(labelText, 4);
    addTextView(view);
    return view;
  }

  @Override
  public Tool addEditAction() {
    final FxButtonTool interaction = FxButtonTool.ForToolbar();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        MigPane mostRecentPanel = buttonPanels.get(buttonPanels.size() - 1);
        mostRecentPanel.add(interaction.getNode());
      }
    });
    return interaction;
  }

  private void addTextView(final FxTextView view) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        pane.add(view.getNode(), new CC().growX());
        MigPane buttonPanel = new MigPane(withoutInsets());
        buttonPanels.add(buttonPanel);
        pane.add(buttonPanel);
      }
    });
  }

  @Override
  public Node getNode() {
    return pane;
  }
}