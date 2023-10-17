import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GpathJsonTests extends FootballConfig {

    @Test
    public void extractMapOfElementsWithFind() {
        Response response = get("competitions/2021/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Manchester United FC'}");

        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find { it.id == 3236 }.name");
        System.out.println("Name of player " + certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFind() {
        Response response = get("teams/57");
        List<String> playerNames = response.path("squad.findAll { it.id >= 7784}.name");
        for (String playerName : playerNames) {
            System.out.println(playerName);
        }
    }

    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response = get("teams/57");
        String playerName = response.path("squad.max { it.id }.name");
        System.out.println(playerName);
    }

    @Test
    public void extractSingleValueWithLowestNumber() {
        Response response = get("teams/57");
        String playerName = response.path("squad.min { it.id }.name");
        System.out.println(playerName);
    }

    @Test
    public void extractMultipleValuesAndSum() {
        Response response = get("teams/57");
        int sumOfIds = response.path("squad.collect { it.id }.sum()");
        System.out.println("Sum of Ids : " + sumOfIds);
    }

    @Test
    public void extractMapWithFindAndFindAllWithParameters() {
        String position = "Offence";
        String nationality = "England";
        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response
                .path("squad.findAll { it.position == '%s'}.find { it.nationality == '%s'}", position, nationality);

        System.out.println(playerOfCertainPosition);

    }

    @Test
    public void extractMultiplePlayers() {
        String position = "Offence";
        String nationality = "England";
        Response response = get("teams/57");
        ArrayList<Map<String, ?>> allPlayerOfCertainPosition = response
                .path("squad.findAll { it.position == '%s'}.findAll { it.nationality == '%s'}", position, nationality);

        System.out.println(allPlayerOfCertainPosition);

    }

}
