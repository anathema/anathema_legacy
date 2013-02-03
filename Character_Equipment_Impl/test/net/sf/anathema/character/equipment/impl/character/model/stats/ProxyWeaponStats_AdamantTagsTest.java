package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.ModifierFactory;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.ReactiveBaseMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.Identified;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.character.equipment.MagicalMaterial.Adamant;
import static net.sf.anathema.character.equipment.MagicalMaterial.Orichalcum;
import static net.sf.anathema.character.equipment.impl.creation.model.WeaponTag.Piercing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProxyWeaponStats_AdamantTagsTest {
  IWeaponStats original = mock(IWeaponStats.class);

  @Test
  public void addsPiercingToAAdamantWeapon() throws Exception {
    setOriginalTags();
    List<Identified> tags = getModifiedTags(Adamant);
    assertThat(tags, JUnitMatchers.<Identified>hasItem(Piercing));
  }

  @Test
  public void doesNotAddPiercingToAnotherMaterial() throws Exception {
    setOriginalTags();
    List<Identified> tags = getModifiedTags(Orichalcum);
    assertThat(tags.size(), is(0));
  }

  @Test
  public void addsSpecialPiercingTagIfItIsAlreadyPiercing() throws Exception {
    setOriginalTags(Piercing);
    List<Identified> tags = getModifiedTags(Adamant);
    assertThat(tags, JUnitMatchers.<Identified>hasItem(new Identifier("AdamantPiercing")));
  }

  private List<Identified> getModifiedTags(MagicalMaterial material) {
    ModifierFactory modifiers = new NullModifierFactory();
    ProxyWeaponStats stats = new ProxyWeaponStats(original, new ReactiveBaseMaterial(material), modifiers);
    return Arrays.asList(stats.getTags());
  }

  private void setOriginalTags(Identified... tags) {
    when(original.getTags()).thenReturn(tags);
  }
}