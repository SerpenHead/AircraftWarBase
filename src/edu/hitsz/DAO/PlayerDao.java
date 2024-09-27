package edu.hitsz.DAO;
import edu.hitsz.DAO.Player;
import java.util.List;

public interface PlayerDao {
    void findById(int PlayerId);

    List<Player> getAllPlayer();

    void doAdd(Player player);

    int getMaxId();

    void sortPlayers();

    void store();

    void load();

    void printRankList(String playerName, int score, int Level);

}