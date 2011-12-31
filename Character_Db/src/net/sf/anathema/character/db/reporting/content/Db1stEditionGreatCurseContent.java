package net.sf.anathema.character.db.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public class Db1stEditionGreatCurseContent extends AbstractSubContent {

  private IGenericCharacter character;

  public Db1stEditionGreatCurseContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "GreatCurse.Dragon-Blooded"; //$NON-NLS-1$
  }

  public String getGreatCurseMessage() {
    String virtueMessage = getVirtueString();
    String aspectMessage = getAspectString();
    return getString("Sheet.GreatCurse.Message", virtueMessage, aspectMessage); //$NON-NLS-1$
  }

  private String getVirtueString() {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    IGenericTrait topVirtue = sortedVirtues.get(0);
    int runnerUpValue = sortedVirtues.get(1).getCurrentValue();
    if (topVirtue.getCurrentValue() == runnerUpValue) {
      return getString("Sheet.GreatCurse.UnknownVirtue"); //$NON-NLS-1$
    }
    return getString(topVirtue.getType().getId());
  }

  private String getAspectString() {
    String casteTypeString = character.getCasteType().getId();
    if (casteTypeString != null) {
      String casteType = getString("Caste." + casteTypeString); //$NON-NLS-1$
      return getString("Sheet.GreatCurse.AspectMessage", casteType); //$NON-NLS-1$
    }
    if (isHighestVirtueKnown()) {
      return getString("Sheet.GreatCurse.UnknownAspectKnownVirtue"); //$NON-NLS-1$
    }
    return getString("Sheet.GreatCurse.UnknownAspectUnknownVirtue"); //$NON-NLS-1$
  }

  private boolean isHighestVirtueKnown() {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    IGenericTrait topVirtue = sortedVirtues.get(0);
    int runnerUpValue = sortedVirtues.get(1).getCurrentValue();
    return topVirtue.getCurrentValue() != runnerUpValue;
  }
}
