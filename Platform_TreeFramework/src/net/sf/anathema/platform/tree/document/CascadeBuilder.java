package net.sf.anathema.platform.tree.document;

public interface CascadeBuilder<GRAPH,CASCADE> {
  void add(GRAPH graph);

  CASCADE create();
}