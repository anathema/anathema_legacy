package net.sf.anathema.character.reporting.first.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.pdf.content.combat.AbstractCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.content.general.QualifiedText;
import net.sf.anathema.character.reporting.pdf.content.general.TextType;
import net.sf.anathema.lib.resources.IResources;

public class CombatStatsContent extends AbstractCombatStatsContent {

  protected CombatStatsContent(IGenericCharacter character, IResources resources) {
    super(resources, character);
  }

  public String getSequenceHeader() {
    return getString("Sheet.Combat.Sequence"); //$NON-NLS-1$
  }

  public String[] getSequenceItems() {
    return new String[] { getString("Sheet.Combat.Sequence.AttackRoll"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.SubtractPenalties"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.DefenceRoll"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.DetermineDamage"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.CheckKnockdown"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.ApplySoak"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.RollDamage"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.ApplyDamage"), //$NON-NLS-1$
      getString("Sheet.Combat.Sequence.CheckStun") //$NON-NLS-1$
    };
  }

  public QualifiedText[] getKnockdownChunks() {
    return new QualifiedText[] { new QualifiedText(getString("Sheet.Combat.Knockdown.Header") + "\n", TextType.Normal), //$NON-NLS-1$ //$NON-NLS-2$
      new QualifiedText("\n" + getString("Sheet.Combat.Knockdown.First.Comment"), TextType.Comment), //$NON-NLS-1$ //$NON-NLS-2$
      new QualifiedText("\n\n" + getString("Sheet.Combat.Comment.First.Rules"), TextType.Comment), //$NON-NLS-1$ //$NON-NLS-2$
    };
  }

  public QualifiedText[] getStunningChunks() {
    return new QualifiedText[] { new QualifiedText(getString("Sheet.Combat.Stunning.Header") + "\n", TextType.Normal), //$NON-NLS-1$ //$NON-NLS-2$
      new QualifiedText("\n" + getResources().getString("Sheet.Combat.Stunning.First.Comment"), TextType.Comment), //$NON-NLS-1$ //$NON-NLS-2$
    };
  }

  public int getInitiative() {
    return CharacterUtilties.getTotalValue(getTraitCollection(), AttributeType.Dexterity, AttributeType.Wits);
  }

  public int getStunningDuration() {
    return Math.max(0, 6 - getStunningThreshold());
  }

  public int getDodgePool() {
    return CharacterUtilties.getDodgePool(getCharacter());
  }

  public String getInitiativeLabel() {
    return getString("Sheet.Combat.BaseInitiative"); //$NON-NLS-1$;
  }

  public String getDodgePoolLabel() {
    return getString("Sheet.Combat.DodgePool"); //$NON-NLS-1$
  }

  public String getMobilityPenaltyLabel() {
    return "-" + getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getThresholdPoolDuratoinLabel() {
    return getString("Sheet.Combat.ThresholdPoolDuration"); //$NON-NLS-1$
  }
}
