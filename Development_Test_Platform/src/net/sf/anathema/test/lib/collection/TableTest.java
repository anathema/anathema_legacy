package net.sf.anathema.test.lib.collection;

import net.sf.anathema.lib.collection.Table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableTest {

  private Table<String, String, String> table;

  @Before
  public void createTable() {
    this.table = new Table<String, String, String>(String.class);
  }

  @Test
  public void testNewTableIsEmpty() throws Exception {
    Assert.assertEquals(0, table.getSize());
  }

  @Test
  public void testRetrieveFromNullCell() throws Exception {
    Assert.assertEquals(null, table.get(null, null));
  }

  @Test
  public void testRetrieveFromEmptyCell() throws Exception {
    String firstKey = "1"; //$NON-NLS-1$
    String secondKey = "2"; //$NON-NLS-1$
    Assert.assertFalse(table.contains(firstKey, secondKey));
    Assert.assertEquals(null, table.get(firstKey, secondKey));
  }

  @Test
  public void testStoreAndRetrieve() throws Exception {
    table.add("1", "2", "3"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Assert.assertEquals("3", table.get("1", "2")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Assert.assertEquals(1, table.getSize());
  }

  @Test
  public void testOverwrite() throws Exception {
    String firstKey = "1"; //$NON-NLS-1$
    String secondKey = "2"; //$NON-NLS-1$
    String firstValue = "First"; //$NON-NLS-1$
    String secondValue = "Second"; //$NON-NLS-1$
    table.add(firstKey, secondKey, firstValue);
    Assert.assertTrue(table.contains(firstKey, secondKey));
    table.add(firstKey, secondKey, secondValue);
    Assert.assertEquals(secondValue, table.get(firstKey, secondKey));
  }

  @Test
  public void testStoreAndRetrieveWithDuplicateKey() throws Exception {
    String string = "1"; //$NON-NLS-1$
    table.add(string, string, string);
    Assert.assertEquals(string, table.get(string, string));
  }

  @Test
  public void testStoreAndRetrieveWithinSameRow() throws Exception {
    String string1 = "1"; //$NON-NLS-1$
    String string2 = "2"; //$NON-NLS-1$
    table.add(string1, string1, string1);
    table.add(string1, string2, string2);
    Assert.assertEquals(string1, table.get(string1, string1));
    Assert.assertEquals(string2, table.get(string1, string2));
  }

  @Test
  public void testStoreAndRetrieveWithinSameColumn() throws Exception {
    String string1 = "1"; //$NON-NLS-1$
    String string2 = "2"; //$NON-NLS-1$
    table.add(string1, string1, string1);
    table.add(string2, string1, string2);
    Assert.assertEquals(string1, table.get(string1, string1));
    Assert.assertEquals(string2, table.get(string2, string1));
  }

  @Test
  public void testRowKeySet() throws Exception {
    String string1 = "1"; //$NON-NLS-1$
    String string2 = "2"; //$NON-NLS-1$
    table.add(string1, string1, string1);
    table.add(string2, string1, string2);
    Assert.assertEquals(2, table.getPrimaryKeys().size());
    Assert.assertTrue(table.getPrimaryKeys().contains(string1));
    Assert.assertTrue(table.getPrimaryKeys().contains(string2));
  }
}