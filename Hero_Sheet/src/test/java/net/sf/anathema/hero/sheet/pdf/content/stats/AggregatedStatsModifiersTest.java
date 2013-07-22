package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.character.main.util.HeroStatsModifiers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AggregatedStatsModifiersTest {

  private final AggregatedStatsModifiers modifiers = new AggregatedStatsModifiers();
  private final HeroStatsModifiers part = mock(HeroStatsModifiers.class);

  @Test
  public void DdvDefaultsToZero() throws Exception {
    assertThat(modifiers.getDDVPoolMod(), is(0));
  }

  @Test
  public void DdvSumsUpParts() throws Exception {
    when(part.getDDVPoolMod()).thenReturn(1);
    modifiers.add(part);
    assertThat(modifiers.getDDVPoolMod(), is(1));
  }

  @Test
  public void JoinBattleDefaultsToZero() throws Exception {
    assertThat(modifiers.getJoinBattleMod(), is(0));
  }

  @Test
  public void JoinBattleSumsUpParts() throws Exception {
    when(part.getJoinBattleMod()).thenReturn(2);
    modifiers.add(part);
    assertThat(modifiers.getJoinBattleMod(), is(2));
  }

  @Test
  public void JoinDebateDefaultsToZero() throws Exception {
    assertThat(modifiers.getJoinDebateMod(), is(0));
  }

  @Test
  public void JoinDebateSumsUpParts() throws Exception {
    when(part.getJoinDebateMod()).thenReturn(3);
    modifiers.add(part);
    assertThat(modifiers.getJoinDebateMod(), is(3));
  }

  @Test
  public void JoinWarDefaultsToZero() throws Exception {
    assertThat(modifiers.getJoinWarMod(), is(0));
  }

  @Test
  public void JoinWarSumsUpParts() throws Exception {
    when(part.getJoinWarMod()).thenReturn(4);
    modifiers.add(part);
    assertThat(modifiers.getJoinWarMod(), is(4));
  }

  @Test
  public void MddvDefaultsToZero() throws Exception {
    assertThat(modifiers.getMDDVPoolMod(), is(0));
  }

  @Test
  public void MddvSumsUpParts() throws Exception {
    when(part.getMDDVPoolMod()).thenReturn(5);
    modifiers.add(part);
    assertThat(modifiers.getMDDVPoolMod(), is(5));
  }

  @Test
  public void MobilityPenaltDefaultsToZero() throws Exception {
    assertThat(modifiers.getMobilityPenalty(), is(0));
  }

  @Test
  public void MobilityPenaltsSumsUpParts() throws Exception {
    when(part.getMobilityPenalty()).thenReturn(6);
    modifiers.add(part);
    assertThat(modifiers.getMobilityPenalty(), is(6));
  }

  @Test
  public void MpdvDefaultsToZero() throws Exception {
    assertThat(modifiers.getMPDVPoolMod(), is(0));
  }

  @Test
  public void MpdvSumsUpParts() throws Exception {
    when(part.getMPDVPoolMod()).thenReturn(7);
    modifiers.add(part);
    assertThat(modifiers.getMPDVPoolMod(), is(7));
  }
}