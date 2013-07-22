package net.sf.anathema.hero.charms.display.view;

public class DefaultFunctionalNodeProperties implements FunctionalNodeProperties {

  @Override
  public final boolean isRequirementNode(final String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }
}