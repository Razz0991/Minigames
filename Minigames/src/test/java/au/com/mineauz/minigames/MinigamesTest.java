package au.com.mineauz.minigames;

import au.com.mineauz.minigames.gametypes.MinigameType;
import au.com.mineauz.minigames.helpers.TestHelper;
import au.com.mineauz.minigames.mechanics.GameMechanics;
import au.com.mineauz.minigames.minigame.Minigame;
import au.com.mineauz.minigames.minigame.modules.LobbySettingsModule;
import au.com.mineauz.minigames.objects.MinigamePlayer;
import au.com.mineauz.minigames.objects.TestPlayer;
import au.com.mineauz.minigames.objects.TestWorld;
import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.command.ConsoleCommandSenderMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.bukkit.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sqlite.SQLiteDataSource;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class MinigamesTest {

    private ServerMock server;
    private Minigames plugin;
    private PlayerMock player;
    private Minigame game;
    private WorldMock world;
    private SQLiteDataSource datasource;
    private Location spawn;
    private Location lobby;
    private Location end;
    private Location start;
    private Location quit;

    @Before
    public void Setup() {
        try {
            server = MockBukkit.mock();
        } catch (IllegalStateException e) {
            server = MockBukkit.getMock();
        }
        ConsoleCommandSenderMock sender = (ConsoleCommandSenderMock) server.getConsoleSender();
        TestWorld testworld = new TestWorld();
        testworld.setName("GAMES");
        MockBukkit.getMock().addWorld(testworld);
        String ver = server.getBukkitVersion();
        Logger log = Logger.getAnonymousLogger();
        log.setLevel(Level.ALL);
        plugin = MockBukkit.load(Minigames.class);
        plugin.toggleDebug();
        plugin.setLog(log);
        world = (WorldMock) MockBukkit.getMock().getWorld("GAMES");
        spawn = world.getSpawnLocation();
        TestHelper.createMinigame(plugin,world,MinigameType.MULTIPLAYER, GameMechanics.MECHANIC_NAME.CTF);
        player = new TestPlayer(MockBukkit.getMock(), "TestPlayer", UUID.randomUUID());
        player.setLocation(spawn);
        MockBukkit.getMock().addPlayer(player);
    }

    @Test
    public void onJoinMinigame() {
        assertNotSame(player.getLocation(),game.getLobbyPosition());
        plugin.getPlayerManager().addMinigamePlayer(player);
        MinigamePlayer mplayer = plugin.getPlayerManager().getMinigamePlayer(player.getUniqueId());
        plugin.getPlayerManager().joinMinigame(mplayer, game, false, 0D);
        LobbySettingsModule module = (LobbySettingsModule) game.getModule("LobbySettings");
        player.assertLocation(lobby, 0);
        assertTrue(module.isTeleportOnStart());
        assertNotSame(game.getStartLocations().indexOf(player.getLocation()), -1);
        server.getScheduler().performTicks(200L);
        player.assertLocation(start,0);
        server.getScheduler().performTicks(200L);
        player.assertLocation(quit,0);

    }

    @Test
    public void onQuitMinigame() {
        plugin.getPlayerManager().addMinigamePlayer(player);
        MinigamePlayer mplayer = plugin.getPlayerManager().getMinigamePlayer(player.getUniqueId());
        plugin.getPlayerManager().joinMinigame(mplayer, game, false, 0D);
        player.assertLocation(lobby, 0);
        Assert.assertTrue(plugin.getPlayerManager().getMinigamePlayer(player.getUniqueId()).isInMinigame());

        plugin.getPlayerManager().quitMinigame(plugin.getPlayerManager().getMinigamePlayer(player), false);
        player.assertLocation(quit, 0);
        assertFalse(plugin.getPlayerManager().getMinigamePlayer(player.getUniqueId()).isInMinigame());
    }

    @Test
    public void testOnDisable(){
        assertTrue(plugin.isEnabled());
        server.getPluginManager().disablePlugin(plugin);
        assertFalse(plugin.isEnabled());
    }
}