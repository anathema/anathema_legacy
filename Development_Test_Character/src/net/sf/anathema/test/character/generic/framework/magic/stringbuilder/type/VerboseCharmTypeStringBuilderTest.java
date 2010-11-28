package net.sf.anathema.test.character.generic.framework.magic.stringbuilder.type;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.VerboseCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.SimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.test.character.generic.framework.magic.stringbuilder.DummyResources;

public class VerboseCharmTypeStringBuilderTest extends AbstractCharmTypeStringBuilderTest {

  private VerboseCharmTypeStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("CharmTreeView.ToolTip.Type", "Type"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("ExtraAction", "Extra Action"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Simple", "Simple"); //$NON-NLS-1$ //$NON-NLS-2$     
    resources.putString("Supplemental", "Supplemental"); //$NON-NLS-1$ //$NON-NLS-2$     
    resources.putString("Reflexive", "Reflexive"); //$NON-NLS-1$ //$NON-NLS-2$     
    resources.putString("CharmTreeView.ToolTip.Type.Speed", "Speed"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.Defense", "DV"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.LongTick", "in long ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.DramaticAction", "Dramatic Action"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.SingleStep", "Step {0, number, integer}"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.SingleStep.Short", "{0, number, integer}"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.DualStep", "Step {0, number, integer} or {1, number, integer}"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Type.DualStep.Short", "{0, number, integer}/{1, number, integer}"); //$NON-NLS-1$ //$NON-NLS-2$
    builder = new VerboseCharmTypeStringBuilder(resources);
  }

  @Override
  protected ICharmTypeStringBuilder getBuilder() {
    return builder;
  }

  public void testReflexiveModelSingleStep() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Reflexive);
    charmTypeModel.setSpecialModel(new ReflexiveSpecialsModel(4, null));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Reflexive (Step 4)", string); //$NON-NLS-1$
  }

  public void testReflexiveModelDoubleStep() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Reflexive);
    charmTypeModel.setSpecialModel(new ReflexiveSpecialsModel(1, 2));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Reflexive (Step 1 or 2)", string); //$NON-NLS-1$
  }

  public void testSimpleModelTickDefaultDefense() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(4, TurnType.Tick, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 4)", string); //$NON-NLS-1$
  }

  public void testSimpleModelTickDefaultSpeed() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(6, TurnType.Tick, -0));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (DV -0)", string); //$NON-NLS-1$
  }

  public void testSimpleModelTick() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.Tick, -2));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 3, DV -2)", string); //$NON-NLS-1$
  }

  public void testSimpleModelDefaultLongTick() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(6, TurnType.LongTick, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 6 in long ticks)", string); //$NON-NLS-1$
  }

  public void testSimpleModelLongTick() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.LongTick, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Speed 3 in long ticks)", string); //$NON-NLS-1$
  }

  public void testSimpleModelDramaticActionDefaultDefense() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.DramaticAction, -1));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Dramatic Action)", string); //$NON-NLS-1$
  }

  public void testSimpleModelDramaticAction() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.Simple);
    charmTypeModel.setSpecialModel(new SimpleSpecialsModel(3, TurnType.DramaticAction, -6));
    String string = builder.createTypeString(charmTypeModel);
    assertEquals("Simple (Dramatic Action, DV -6)", string); //$NON-NLS-1$
  }
}