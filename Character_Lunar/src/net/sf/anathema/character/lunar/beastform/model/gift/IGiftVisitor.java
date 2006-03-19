package net.sf.anathema.character.lunar.beastform.model.gift;

public interface IGiftVisitor {

  public void visitAttributePointsProvidingGift(AttributePointsProvidingGift gift);

  public void visitAttributeEnhancingGift(AttributeEnhancingGift gift);

  public void acceptSoakProvidingGift(SoakProvidingGift gift);

  public void acceptBrawlWeaponProvidingGift(BrawlWeaponProvidingGift gift);
}