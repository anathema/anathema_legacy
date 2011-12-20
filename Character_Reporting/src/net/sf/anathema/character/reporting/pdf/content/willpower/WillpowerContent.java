package net.sf.anathema.character.reporting.pdf.content.willpower;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.character.reporting.pdf.content.general.BulletList;
import net.sf.anathema.character.reporting.pdf.rendering.general.ListUtils;
import net.sf.anathema.lib.resources.IResources;

public class WillpowerContent extends AbstractSubContent {

  private IGenericCharacter character;

  public WillpowerContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Willpower"; //$NON-NLS-1$
  }

  public int getWillpowerValue() {
    return character.getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
  }

  public IExaltedEdition getEdition() {
    return character.getRules().getEdition();
  }

  public String getWillpowerSpendingNote() {
    return getString("Sheet.WillpowerSpendingNote");
  }

  public BulletList getWillpowerSpendingRules() {
    return createBulletList("Sheet.WillpowerSpendingRules");
  }

  public BulletList getWillpowerRegainingRules() {
    return createBulletList("Sheet.WillpowerRegainingRules");
  }

  private BulletList createBulletList(String resourceBase) {
    String header = ListUtils.getRequiredString(getResources(), getEdition(), resourceBase);
    String[] items = ListUtils.getAvailableLineItems(getResources(), getEdition(), resourceBase);
    return new BulletList(header, items);
  }
}
