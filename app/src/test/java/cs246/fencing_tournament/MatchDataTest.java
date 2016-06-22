package cs246.fencing_tournament;

import org.junit.Test;

import cs246.fencing_tournament.data.MatchData;

public class MatchDataTest {
    @Test
    public void testConstructor() throws Exception {
        MatchData cd = new MatchData(2, 3);

        System.err.println("Id1: " + cd.getId1() + "\nId2: " + cd.getId2());
        assert(cd.getVicId() == -1);
        assert(cd.getId1() == 2);
        assert(cd.getId2() == 3);
    }

    @Test
    public void testSetters() throws Exception {
        MatchData cd = new MatchData(2, 3);

        System.err.println("Using correct data for setters");
        cd.setId1(3);
        cd.setId2(4);
        assert(cd.getId1() == 3);
        assert(cd.getId2() == 4);
        if (cd.getId1() == 3 && cd.getId2() == 4)
            System.err.println("The setters worked!");

        System.err.println("Using incorrect data for setters");
        cd.setId1(-1);
        cd.setId2(-1);
        assert(cd.getId1() >= 0);
        assert(cd.getId2() >= 0);
        if (cd.getId1() >= 0 && cd.getId2() >= 0)
            System.err.println("The setters worked!");
        else
            System.err.println("The setters didn't worked!");
    }

}
