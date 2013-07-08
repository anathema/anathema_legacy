package net.sf.anathema.fx.hero.configurableview;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.fx.hero.traitview.FxTraitView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxConfigurableView implements ConfigurableCharacterView, NodeHolder {
  private MigPane pane = new MigPane(withoutInsets().wrapAfter(2));
  private final List<MigPane> buttonPanels = new ArrayList<>();

  @Override
  public MultiComponentLine addMultiComponentLine() {
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

  @Override
  public <T> IObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> uiConfiguration) {
    final FxObjectSelectionView<T> selectionView = new ComboBoxSelectionView<>(label, uiConfiguration);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        pane.add(selectionView.getNode(), new CC().growX().wrap());
      }
    });
    return selectionView;
  }

  @Override
  public IIntValueView addDotSelector(String label, int maxValue) {
    final FxTraitView view = FxTraitView.AsSingleNode(label, maxValue);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        view.addTo(pane);
      }
    });
    return view;
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