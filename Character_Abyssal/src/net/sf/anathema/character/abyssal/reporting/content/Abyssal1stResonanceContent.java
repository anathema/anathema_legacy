package net.sf.anathema.character.abyssal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public class Abyssal1stResonanceContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public Abyssal1stResonanceContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "GreatCurse.Abyssal"; //$NON-NLS-1$
  }

  public String getSocialPool() {
    int maxVirtueValue = getMaxVirtueValue();
    return getString("Sheet.GreatCurse.SocialPoolMessage", maxVirtueValue);  //$NON-NLS-1$
  }

  private int getMaxVirtueValue() {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    return sortedVirtues.get(0).getCurrentValue();
  }

  public Object getVirtueDifficultyLabel() {
    return getString("Sheet.GreatCurse.VirtueDifficulty");
  }
}
