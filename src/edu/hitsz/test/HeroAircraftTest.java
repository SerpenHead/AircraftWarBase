package edu.hitsz.test;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeroAircraftTest {

    private HeroAircraft heroAircraft;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        heroAircraft = HeroAircraft.getInstance();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--- Executed after each test method in this class ---**");
        heroAircraft = null;
    }

    @Test
    @DisplayName("Test getInstance method")
    void testGetInstance() {
        System.out.println("**--- Test getInstance method executed ---**");
        HeroAircraft heroAircraft1 = HeroAircraft.getInstance();
        HeroAircraft heroAircraft2 = HeroAircraft.getInstance();
        System.out.println("**--- 创建成功 ---**");
        assertEquals(heroAircraft1, heroAircraft2);
    }

    @Test
    @DisplayName("Test shoot method")
    void testShoot() {
        System.out.println("**--- Test shoot method executed ---**");
        List<BaseBullet> bullets = heroAircraft.shoot();
        assertNotNull(bullets);
        assertEquals(2, bullets.size());
        for (BaseBullet bullet : bullets) {
            assertTrue(bullet instanceof HeroBullet);
        }
    }

    // Add more tests as needed

    @AfterAll
    static void afterAll() {
        System.out.println("**--- Executed once after all test methods in this class ---**");
    }
}
