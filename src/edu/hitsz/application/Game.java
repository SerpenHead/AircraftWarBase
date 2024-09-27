package edu.hitsz.application;

import edu.hitsz.DaoList;
import edu.hitsz.EnemyCraftFactory.BossEnemyFactory;
import edu.hitsz.EnemyCraftFactory.EliteEnemyFactory;
import edu.hitsz.EnemyCraftFactory.ElitePlusEnemyFactory;
import edu.hitsz.EnemyCraftFactory.MobEnemyFactory;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.supply.BaseSupply;
import edu.hitsz.music.MusicThread;
import edu.hitsz.DAO.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;
    private final EliteEnemyFactory eliteEnemyFactory = new EliteEnemyFactory();
    private final MobEnemyFactory mobEnemyFactory = new MobEnemyFactory();
    private final ElitePlusEnemyFactory elitePlusEnemyFactory = new ElitePlusEnemyFactory();
    private final BossEnemyFactory bossEnemyFactory = new BossEnemyFactory();

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    public final List<BaseSupply> Supplies;
    private BufferedImage BackgroundImage;

    private int backGroundTop = 0;
    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;
    /**
     * 屏幕中出现的敌机最大数量
     */
    private int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;
    private int boss_exit_flag = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;
    private int Difficulty_level_kind;
    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;
    private MusicThread bgmThread = new MusicThread("src/videos/bgm.wav");
    private static MusicThread BossBgmThread = new MusicThread("src/videos/bgm_boss.wav");

    public Game(int BackgroundImage_kind, int music_kind) {
        heroAircraft = HeroAircraft.getInstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        Supplies = new LinkedList<>();
        Difficulty_level_kind = BackgroundImage_kind;
        switch (BackgroundImage_kind) {
            case 1:
                BackgroundImage = ImageManager.BACKGROUND_IMAGE_1;
                break;
            case 2:
                BackgroundImage = ImageManager.BACKGROUND_IMAGE_2;
                break;
            case 3:
                BackgroundImage = ImageManager.BACKGROUND_IMAGE_3;
        }
        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        // 启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable game_task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    /* 产生精英敌机 */
                    if (score < 100 || score % 2000 > 60) {
                        double it = Math.random();
                        if (it < 0.15) {
                            if (it < 0.05) {
                                enemyAircrafts.add(elitePlusEnemyFactory.creatElitePlusEnemy());
                            }
                            enemyAircrafts.add(eliteEnemyFactory.creatEliteEnemy());
                        } else {
                            enemyAircrafts.add(mobEnemyFactory.creatMobEnemy());
                        }
                    } else if (boss_exit_flag == 0) {
                        enemyAircrafts.add(bossEnemyFactory.creatBossEnemy());
                        boss_exit_flag = 1;
                        BossBgmThread.start();
                        bgmThread.stop_music();
                    }

                }

                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            SuppliesMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            // 每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
                playerDaoImpl.printRankList("Nimo", score,Difficulty_level_kind);
                DaoList rank_menu = new DaoList(playerDaoImpl.get_PlayerTable(), playerDaoImpl);
                Main.cardPanel.add(rank_menu.getMenuMainPanel());
                Main.cardLayout.last(Main.cardPanel);
                bgmThread.stop_music();
                BossBgmThread.stop_music();
                (new MusicThread("src/videos/game_over.wav")).start();
                System.out.println("Game Over!");
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(game_task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
        bgmThread.setIsLoop(true);
        bgmThread.start();
    }

    // ***********************
    // Action 各部分
    // ***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for (AbstractAircraft enemyCraft : enemyAircrafts) {
            enemyBullets.addAll(enemyCraft.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void SuppliesMoveAction() {
        for (BaseSupply supply : Supplies) {
            supply.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            /* 如果子弹击中英雄 */
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
            /* 检查英雄血量是否健康 */
            if (heroAircraft.notValid()) {
                for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                    enemyAircraft.vanish();
                }
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    (new MusicThread("src/videos/bullet_hit.wav")).start();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score = enemyAircraft.increaseScore(score);
                        if(enemyAircraft  instanceof MobEnemy){
                            continue;
                        }
                        else
                            enemyAircraft.creatSupply(Supplies);
                        if(enemyAircraft instanceof BossEnemy){
                            BossBgmThread.stop_music();
                            bgmThread.start();
                        }

                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (BaseSupply supply : Supplies) {
            if (heroAircraft.crash(supply)) {
                (new MusicThread("src/videos/get_supply.wav")).start();
                supply.get_effect(heroAircraft);
            }
        }

    }



    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        Supplies.removeIf(AbstractFlyingObject::notValid);
    }

    // ***********************
    // Paint 各部分
    // ***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(BackgroundImage, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(BackgroundImage, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, Supplies);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        // 绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

}
