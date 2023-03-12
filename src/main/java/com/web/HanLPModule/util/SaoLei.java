package com.web.HanLPModule.util;

import java.util.Scanner;

public class SaoLei {
    public static void main(String[] args) {
        // 初始化地图
        int[][] map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = 0;
            }
        }
        // 随机放置雷
        int mineNum = 10;
        while (mineNum > 0) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if (map[x][y] != 9) {
                map[x][y] = 9;
                mineNum--;
            }
        }
        // 计算每个格子周围雷的数量
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 9) {
                    continue;
                }
                int count = 0;
                if (i > 0 && j > 0 && map[i - 1][j - 1] == 9) {
                    count++;
                }
                if (i > 0 && map[i - 1][j] == 9) {
                    count++;
                }
                if (i > 0 && j < 9 && map[i - 1][j + 1] == 9) {
                    count++;
                }
                if (j > 0 && map[i][j - 1] == 9) {
                    count++;
                }
                if (j < 9 && map[i][j + 1] == 9) {
                    count++;
                }
                if (i < 9 && j > 0 && map[i + 1][j - 1] == 9) {
                    count++;
                }
                if (i < 9 && map[i + 1][j] == 9) {
                    count++;
                }
                if (i < 9 && j < 9 && map[i + 1][j + 1] == 9) {
                    count++;
                }
                map[i][j] = count;
            }
        }
        // 打印地图
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        // 开始游戏
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要排查的坐标：");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (map[x][y] == 9) {
                System.out.println("Game Over!");
                break;
            } else {
                System.out.println("周围雷的数量：" + map[x][y]);
            }
        }
    }
}

