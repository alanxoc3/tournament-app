package cs246.fencing_tournament;

import org.junit.Test;

import cs246.fencing_tournament.data.ContestantData;

import static org.junit.Assert.*;

/**
 * Created by alanxoc3 on 6/8/16.
 */
public class ContestantDataTest {
    @Test
    public void testName() throws Exception {
        ContestantData cd = new ContestantData("hello");

        System.err.println("Name is: " + cd.getName());
        assert(cd.getName().equals("hello"));
    }

    @Test
    public void testId() {
        final int len = 10;
        ContestantData[] contestants = new ContestantData[len];

        for (int i = 0; i < len; ++i) {
            contestants[i] = new ContestantData();
        }

        System.out.println("Ids:");
        for (ContestantData it : contestants) System.out.println(it.getId());

        for (int i = 0; i < len; ++i) {
            assertEquals(i + contestants[0].getId(), contestants[i].getId());
        }
    }
}
