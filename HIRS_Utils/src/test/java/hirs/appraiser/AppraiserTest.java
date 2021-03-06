package hirs.appraiser;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for the class <code>Appraiser</code>.
 */
public final class AppraiserTest {

    /**
     * Tests that an <code>Appraiser</code> can be created with a valid name.
     */
    @Test
    public void testAppraiser() {
        final String name = "Test Appraiser";
        new TestAppraiser(name);
    }

    /**
     * Tests that <code>Appraiser</code> throws a <code>NullPointerException</code> if a name is not
     * provided.
     */
    @Test(expectedExceptions = NullPointerException.class)
    public void testAppraiserNullName() {
        new TestAppraiser(null);
    }

    /**
     * Tests that the name is returned from <code>getName</code>.
     */
    @Test
    public void testGetName() {
        final String name = "Test Appraiser";
        final Appraiser appraiser = new TestAppraiser(name);
        Assert.assertEquals(appraiser.getName(), name);
    }

    /**
     * Tests that the name property can be set.
     */
    @Test
    public void testSetName() {
        final String originalName = "Test Appraiser";
        final Appraiser appraiser = new TestAppraiser(originalName);
        Assert.assertEquals(appraiser.getName(), originalName);
        final String newName = "Awesome Test Appraiser";
        appraiser.setName(newName);
        Assert.assertEquals(appraiser.getName(), newName);
    }

    /**
     * Tests that a <code>NullPointerException</code> is thrown if the name is null.
     */
    @Test
    public void testSetNameNull() {
        final String name = "Test Appraiser";
        final Appraiser appraiser = new TestAppraiser(name);
        Assert.assertEquals(appraiser.getName(), name);
        NullPointerException expected = null;
        try {
            appraiser.setName(null);
        } catch (NullPointerException e) {
            expected = e;
        }
        Assert.assertNotNull(expected, "NullPointerException not caught");
        Assert.assertEquals(appraiser.getName(), name);
    }

    /**
     * Tests that x.equals(null) returns false.
     */
    @Test
    public void testEqualsNull() {
        final String name = "Test Appraiser";
        final Appraiser appraiser = new TestAppraiser(name);
        Assert.assertFalse(appraiser.equals(null));
    }

    /**
     * Tests that x.equals(x) for an appraiser.
     */
    @Test
    public void testEqualsReflexive() {
        final String name = "Test Appraiser";
        final Appraiser appraiser = new TestAppraiser(name);
        Assert.assertTrue(appraiser.equals(appraiser));
    }

    /**
     * Tests that x.equals(y) and y.equals(x) for an appraiser.
     */
    @Test
    public void testEqualsSymmetric() {
        final String name = "Test Appraiser";
        final Appraiser appraiser1 = new TestAppraiser(name);
        final Appraiser appraiser2 = new TestAppraiser(name);
        Assert.assertTrue(appraiser1.equals(appraiser2));
        Assert.assertTrue(appraiser2.equals(appraiser1));
    }

    /**
     * Tests that x.equals(y) and y.equals(z) then x.equals(z) for an appraiser.
     */
    @Test
    public void testEqualsTransitive() {
        final String name = "Test Appraiser";
        final Appraiser appraiser1 = new TestAppraiser(name);
        final Appraiser appraiser2 = new TestAppraiser(name);
        final Appraiser appraiser3 = new TestAppraiser(name);
        Assert.assertTrue(appraiser1.equals(appraiser2));
        Assert.assertTrue(appraiser2.equals(appraiser3));
        Assert.assertTrue(appraiser1.equals(appraiser3));
    }

    /**
     * Tests that two appraisers are not equal if their names are different.
     */
    @Test
    public void testNotEquals() {
        final String name1 = "Test Appraiser";
        final String name2 = "Other Appraiser";
        final Appraiser appraiser1 = new TestAppraiser(name1);
        final Appraiser appraiser2 = new TestAppraiser(name2);
        Assert.assertFalse(appraiser1.equals(appraiser2));
        Assert.assertFalse(appraiser2.equals(appraiser1));
    }

    /**
     * Tests that if two appraisers are equal that their hash codes are equal.
     */
    @Test
    public void testHashCodeEquals() {
        final String name = "Test Appraiser";
        final Appraiser appraiser1 = new TestAppraiser(name);
        final Appraiser appraiser2 = new TestAppraiser(name);
        Assert.assertTrue(appraiser1.equals(appraiser2));
        Assert.assertTrue(appraiser2.equals(appraiser1));
        Assert.assertEquals(appraiser1.hashCode(), appraiser2.hashCode());
        Assert.assertEquals(appraiser2.hashCode(), appraiser1.hashCode());
    }

    /**
     * Tests that if two appraisers are not equal that their hash codes are not equal.
     */
    @Test
    public void testHashCodeNotEquals() {
        final String name1 = "Test Appraiser";
        final String name2 = "Other Appraiser";
        final Appraiser appraiser1 = new TestAppraiser(name1);
        final Appraiser appraiser2 = new TestAppraiser(name2);
        Assert.assertFalse(appraiser1.equals(appraiser2));
        Assert.assertFalse(appraiser2.equals(appraiser1));
        Assert.assertNotEquals(appraiser1.hashCode(), appraiser2.hashCode());
        Assert.assertNotEquals(appraiser2.hashCode(), appraiser1.hashCode());
    }

}
