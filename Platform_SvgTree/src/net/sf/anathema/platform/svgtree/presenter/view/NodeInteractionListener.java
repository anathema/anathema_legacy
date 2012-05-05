package net.sf.anathema.platform.svgtree.presenter.view;

public interface NodeInteractionListener {

  void nodeSelected(String nodeId);

  void nodeDetailsDemanded(String nodeId);
}