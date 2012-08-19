package net.sf.anathema.platform.tree.document;

public interface CascadeBuilder<GRAPH,CASCADE> {
  void initialize();

  void add(GRAPH graph);

  void applyFinalTouch(double currentWidth, double maximumHeight);

  CASCADE create();
}