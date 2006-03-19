package net.sf.anathema.character.generic.framework.magic.compare;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.CharmGraphNodeProvider;
import net.sf.anathema.character.generic.framework.magic.treelayout.layering.TopologyBuilder;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IIdentifiedRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IRegularNode;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class MagicComparator implements Comparator<IMagic> {

  private final CharacterType characterType;
  private final Map<String, Map<String, Integer>> charmOrderByGroupId = new HashMap<String, Map<String, Integer>>();

  public MagicComparator(CharacterType characterType) {
    this.characterType = characterType;
  }

  public int compare(IMagic magic1, final IMagic magic2) {
    final int[] compareValue = new int[1];
    magic1.accept(new IMagicVisitor() {
      public void visitSpell(ISpell spell) {
        compareValue[0] = compareToSpell(spell, magic2);
      }

      public void visitCharm(ICharm charm) {
        compareValue[0] = compareToRegularCharm(charm, magic2);
      }

      public void visitMartialArtsCharm(IMartialArtsCharm charm) {
        compareValue[0] = compareToMartialArtsCharm(charm, magic2);
      }
    });
    return compareValue[0];
  }

  private int compareToSpell(final ISpell spell, IMagic magic2) {
    final int[] compareValue = new int[1];
    magic2.accept(new IMagicVisitor() {
      public void visitSpell(ISpell otherSpell) {
        compareValue[0] = spell.getCircleType().compareTo(otherSpell.getCircleType());
        if (compareValue[0] == 0) {
          compareValue[0] = spell.getId().compareTo(otherSpell.getId());
        }
      }

      public void visitCharm(ICharm charm) {
        compareValue[0] = 2;
      }

      public void visitMartialArtsCharm(IMartialArtsCharm charm) {
        compareValue[0] = 1;
      }
    });
    return compareValue[0];
  }

  private int compareToRegularCharm(final ICharm charm, IMagic magic2) {
    final int[] compareValue = new int[1];
    magic2.accept(new IMagicVisitor() {
      public void visitMartialArtsCharm(IMartialArtsCharm otherCharm) {
        compareValue[0] = -1;
      }

      public void visitSpell(ISpell spell) {
        compareValue[0] = -2;
      }

      public void visitCharm(ICharm otherCharm) {
        CharacterType charmCharacterType = charm.getCharacterType();
        CharacterType otherCharacterType = otherCharm.getCharacterType();
        if (charmCharacterType != otherCharacterType) {
          if (charmCharacterType == characterType) {
            compareValue[0] = -1;
          }
          else if (otherCharacterType == characterType) {
            compareValue[0] = 1;
          }
          else {
            compareValue[0] = charmCharacterType.compareTo(otherCharacterType);
          }
        }
        else {
          ICharm[] charms;
          // todo vom (31.10.2005) (sieroux): ErrorHandling
          try {
            charms = CharmCache.getInstance().getCharms(otherCharacterType, false);
            compareValue[0] = sortEqualTypeCharms(charm, otherCharm, charms);
          }
          catch (PersistenceException e) {
            e.printStackTrace();
          }
        }
      }
    });
    return compareValue[0];
  }

  private int sortEqualTypeCharms(ICharm charm, ICharm otherCharm, ICharm[] allCarms) {
    String groupId = charm.getGroupId();
    int compareValue = groupId.compareTo(otherCharm.getGroupId());
    if (compareValue == 0) {
      compareValue = compareSameTypeSameGroupCharms(charm, otherCharm, allCarms);
    }
    return compareValue;
  }

  private int compareSameTypeSameGroupCharms(ICharm charm, ICharm otherCharm, ICharm[] allCharms) {
    Map<String, Integer> charmOrder;
    String typeGroupId = charm.getCharacterType().getId() + "." + charm.getGroupId(); //$NON-NLS-1$
    if (charmOrderByGroupId.containsKey(typeGroupId)) {
      charmOrder = charmOrderByGroupId.get(typeGroupId);
    }
    else {
      CharmGraphNodeProvider provider = new CharmGraphNodeProvider(allCharms);
      IIdentifiedRegularNode[] nodesForGroup = provider.getNodesForGroup(charm.getGroupId());
      IRegularNode[] nodes = TopologyBuilder.sortGraphByTopology(nodesForGroup);
      charmOrder = new HashMap<String, Integer>();
      for (int nodeIndex = 0; nodeIndex < nodes.length; nodeIndex++) {
        charmOrder.put(((IIdentifiedRegularNode) nodes[nodeIndex]).getId(), nodeIndex);
      }
      charmOrderByGroupId.put(typeGroupId, charmOrder);
    }
    return charmOrder.get(charm.getId()) - charmOrder.get(otherCharm.getId());
  }

  private int compareToMartialArtsCharm(final IMartialArtsCharm charm, IMagic magic2) {
    final int[] compareValue = new int[1];
    magic2.accept(new IMagicVisitor() {
      public void visitCharm(ICharm otherCharm) {
        if (otherCharm.getCharacterType() == characterType) {
          compareValue[0] = 1;
        }
        else {
          compareValue[0] = -1;
        }
      }

      public void visitSpell(ISpell spell) {
        compareValue[0] = -1;
      }

      public void visitMartialArtsCharm(IMartialArtsCharm otherCharm) {
        IMartialArtsCharm[] martialArtsCharms;
        // todo vom (31.10.2005) (sieroux): ErrorHandling
        try {
          martialArtsCharms = CharmCache.getInstance().getMartialArtsCharms(false);
          compareValue[0] = sortEqualTypeCharms(charm, otherCharm, martialArtsCharms);
        }
        catch (PersistenceException e) {
          e.printStackTrace();
        }
      }
    });
    return compareValue[0];
  }
}