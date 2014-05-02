package net.sf.anathema.framework.repository.tree;

import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.list.veto.Vetor;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class RepositoryTreeView implements IRepositoryTreeView {
  private final MigPane panel = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final MigPane buttonPanel = new MigPane(LayoutUtils.fillWithoutInsets());

  public RepositoryTreeView() {
    panel.add(buttonPanel, new CC().pushX().growX());
  }

  @Override
  public Vetor createVetor(String message, String title) {
    return new FxVetor(title, message);
  }

  @Override
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    buttonPanel.add(tool.getNode(), new CC().grow());
    return tool;
  }

  @Override
  public AgnosticTree addAgnosticTree() {
    TreeView<Object> treeView = new TreeView<>();
    treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    treeView.setPrefHeight(300);
    panel.add(treeView, new CC().grow().push());
    return new FxTree(treeView);
  }

  public Node getNode() {
    return panel;
  }
}