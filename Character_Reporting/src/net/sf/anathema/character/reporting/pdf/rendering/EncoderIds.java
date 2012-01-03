package net.sf.anathema.character.reporting.pdf.rendering;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes.AttributesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.SimpleEssenceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementEncoder;

public interface EncoderIds {

  String ARSENAL = "WeaponryEncoder";
  String ATTRIBUTES = AttributesEncoder.class.getName();
  String COMBAT = CombatStatsContentBoxEncoder.class.getName();
  String ESSENCE_SIMPLE = SimpleEssenceBoxContentEncoder.class.getName();
  String EXPERIENCE = ExperienceBoxContentEncoder.class.getName();
  String HEALTH_AND_MOVEMENT = AbstractHealthAndMovementEncoder.class.getName();
  String MERITS_AND_FLAWS = "MeritsAndFlaws";
  String SOCIAL_COMBAT = "SocialCombat";
}
