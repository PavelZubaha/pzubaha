package pzubaha.threads.cache;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Chapter_006. Multithreading.
 * 5. Non-blocking algorithm.
 * 1. Non blocking cache.
 * <p>
 * Contains solution of task 4741.
 * Class implements simple non-blocking cache.
 * Created 28.04.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ModelCacheTest {
    private ModelCache<Integer, String> testedCache;
    private String firstValueToAdd = String.valueOf("First value");
    private String secondValueToAdd = String.valueOf("Second value");
    @Before
    public void setUp() {
        testedCache = new ModelCache<>();
    }

    @Test
    public void addAddSomeValueThenItStored() {
        assertTrue(testedCache.add(1, new Item<>(firstValueToAdd)));
        assertTrue(testedCache.add(2, new Item<>(secondValueToAdd)));
        assertThat(testedCache.get(1).getContext(), is(firstValueToAdd));
        assertFalse(testedCache.add(1, new Item<>("Another one")));
        assertThat(testedCache.get(2).getContext(), is(secondValueToAdd));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateItemThenStoredNewValue() {
        String updatedValue = String.valueOf("updated");
        testedCache.add(1, new Item<>(firstValueToAdd));
        Item<String> itemToUpdate = new Item<>(updatedValue);
        Item<String> nextItemToTestException = new Item<>(secondValueToAdd);
        assertTrue(testedCache.update(1, itemToUpdate));
        assertThat(testedCache.get(1).getContext(), is(updatedValue));
        testedCache.update(1, nextItemToTestException);
    }

    @Test
    public void testDeleteMethod() {
        assertFalse(testedCache.delete(1));
        testedCache.add(1, new Item<>(firstValueToAdd));
        assertTrue(testedCache.delete(1));
    }

    @Test
    public void whenThereIsNoValueAssociatedWithTheKeyThanReturnedItemWithNullContext() {
        assertNull(testedCache.get(1).getContext());
        Item<String> added = new Item<>(firstValueToAdd);
        testedCache.add(1, added);
        assertThat(testedCache.get(1), is(added));
    }
}