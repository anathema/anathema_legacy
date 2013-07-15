package net.sf.anathema.platform.tree.display;

public interface NodeInteractionListener {

  void nodeSelected(String nodeId);

  void nodeDetailsDemanded(String nodeId);
}