package net.sf.anathema.charmtree.builder.stringbuilder.type;

import net.sf.anathema.charmtree.builder.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.SimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerboseCharmTypeStringBuilderTest extends AbstractCharmTypeStringBuilderTest {

  private VerboseCharmTypeStringBuilder builder;

  @Before
  public void setUp() throws Exception {
    DummyResources resources = new DummyResources();
    resources.putString("CharmTreeView.ToolTip.Type", "Type");
    resources.putString("ExtraAction", "Extra Action");
    resources.putString("Simple", "Simple");
    resources.putString("Supplemental", "Supplemental");
    resources.putString("Reflexive", "Reflexive");
    resources.putString("CharmTreeView.ToolTip.Type.Speed", "Speed");
    resources.putString("CharmTreeView.ToolTip.Type.Defense", "DV");
    resources.putString("CharmTreeView.ToolTip.Type.LongTick", "in long ticks");
    resources.putString("CharmTreeView.ToolTip.Type.DramaticAction", "Dramatic Action");
    resources.putString("CharmTreeView.ToolTip.Type.SingleStep", "Step {0, number, integer}");
    resources.putString("CharmTreeView.ToolTip.Type.SingleStep.Short", "{0, number, integer}");
    resources.putString("CharmTreeView.ToolTip.Type.DualStep", "Step {0, number, integer} or {1, number, integer}");
    resources.putString("CharmTreeView.ToolTip.Type.DualStep.Short", "{0, number, integer}/{1, number, integer}");
    builder = new VerboseCharmTypeStringBuilder(resources);
  }

  @Override
  protected ICharmTypeStringBuilder getBuilder() {
    return builder;
  }

  @Test
  public void testReflexiveModelSingleStep() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Reflexive);
    charmTypeModel.setSpecialModel(new ReflexiveSpecialsModel(4, null));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Reflexive (Step 4)", string);
  }

  @Test
  public void testReflexiveModelDoubleStep() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Reflexive);
    charmTypeModel.setSpecialModel(new ReflexiveSpecialsModel(1, 2));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Reflexive (Step 1 or 2)", string);
  }

  @Test
  public void testSimpleModelTickDefaultDefense() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(4, TurnType.Tick, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 4)", string);
  }

  @Test
  public void testSimpleModelTickDefaultSpeed() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(6, TurnType.Tick, -0));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (DV -0)", string);
  }

  @Test
  public void testSimpleModelTick() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.Tick, -2));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 3, DV -2)", string);
  }

  @Test
  public void testSimpleModelDefaultLongTick() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(6, TurnType.LongTick, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 6 in long ticks)", string);
  }

  @Test
  public void testSimpleModelLongTick() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.LongTick, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 3 in long ticks)", string);
  }

  @Test
  public void testSimpleModelDramaticActionDefaultDefense() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.DramaticAction, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Dramatic Action)", string);
  }

  @Test
  public void testSimpleModelDramaticAction() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.DramaticAction, -6));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Dramatic Action, DV -6)", string);
  }
}