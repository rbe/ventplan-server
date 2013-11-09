package eu.artofcoding.ventplan.api;

import com.google.common.base.Optional;
import eu.artofcoding.ventplan.api.vpx.Luftart;
import eu.artofcoding.ventplan.api.vpx.Room;
import eu.artofcoding.ventplan.api.vpx.Ventilebene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class CalculationTest {

    private static List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        Room roomZu = new Room();
        roomZu.setLuftart(Luftart.ZU);
        roomZu.setVentilebene(Ventilebene.EG);
        rooms.add(roomZu);
        Room roomAb = new Room();
        roomAb.setLuftart(Luftart.AB);
        roomAb.setVentilebene(Ventilebene.OG);
        rooms.add(roomAb);
        Room roomZuAb = new Room();
        roomZuAb.setLuftart(Luftart.ZUA);
        roomZuAb.setVentilebene(Ventilebene.DG);
        rooms.add(roomZuAb);
        return rooms;
    }

    @Test
    public void guavaOptionalTest() {
        double a = 1.0d;
        Optional<Double> optinalB = Optional.fromNullable(null);
        double c = 3.0d;
        assertThat(a + optinalB.or(0.0d) + c, is(4.0d));
    }

    @Test
    public void testFilterZuluftRaumeByLuftart() {
        // Create list with rooms
        List<Room> rooms = getRooms();
        // Extract rooms
        List<Room> rooms1 = Calculation.filterRaume(rooms, Luftart.ZU, Luftart.ZUA);
        // Check
        assertThat(rooms1, hasItems(rooms.get(0), rooms.get(2)));
    }

    @Test
    public void testFilterZuluftRaumeByString() {
        // Create list with rooms
        List<Room> rooms = new ArrayList<>();
        Room roomZu = new Room();
        roomZu.setLuftart(Luftart.ZU);
        rooms.add(roomZu);
        Room roomAb = new Room();
        roomAb.setLuftart(Luftart.AB);
        rooms.add(roomAb);
        Room roomZuAb = new Room();
        roomZuAb.setLuftart(Luftart.ZUA);
        rooms.add(roomZuAb);
        // Extract rooms
        List<Room> rooms1 = Calculation.filterRaume(rooms, "ZU", "ZU/AB");
        // Check
        assertThat(rooms1, hasItems(rooms.get(0), rooms.get(2)));
    }

    @Test
    public void testGroupByVentilebene() {
        List<Room> rooms = getRooms();
        Map<Ventilebene,Room> ventilebeneRoomMap = Calculation.groupByVerteilebenen(rooms);
    }

}
