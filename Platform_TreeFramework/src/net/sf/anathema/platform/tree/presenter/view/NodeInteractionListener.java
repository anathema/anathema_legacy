package net.sf.anathema.platform.tree.presenter.view;

public interface NodeInteractionListener {

  void nodeSelected(String nodeId);

  void nodeDetailsDemanded(String nodeId);
}