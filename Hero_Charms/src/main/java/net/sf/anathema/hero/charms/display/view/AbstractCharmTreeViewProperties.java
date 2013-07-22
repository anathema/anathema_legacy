package net.sf.anathema.hero.charms.display.view;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  @Override
  public final boolean isRequirementNode(final String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }
}