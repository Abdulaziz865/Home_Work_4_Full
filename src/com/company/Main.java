package com.company;

import java.util.Random;

public class Main {


            public static int bossHealth = 700;
            public static int bossDamage = 50;
            public static String bossDefence = "";

            public static int[] heroesHealth = {250, 110, 320, 500, 200,190,200, 280};
            public static int[] heroesDamage = {15, 10, 20, 25, 5,18,30, 0};
            public static String[] heroesAttakTypes = {"Physical", "Magical", "Kinetic", "Golem", "Lucky","Berserk","Thor", "Medic"};




            public static void medicHeal() {
                int index = 0;
                int help = 20;
                for (String name : heroesAttakTypes) {
                    if (name.equals("Medic")) {
                        for (int i = 0; i < heroesHealth.length; i++) {
                            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[index] && heroesHealth[index] > 0) {
                                heroesHealth[i] += help;
                                System.out.println("Medic help: " + heroesAttakTypes[i]);
                                break;
                            }
                        }

                    }
                    index++;

                }

            }
            public static void golemTanking(){
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[4] > 0){
                        heroesHealth[4] -= bossDamage * 1 / 5;
                        heroesHealth[i] += bossDamage * 1 / 5;
                        System.out.println("Golem get: " + bossDamage);
                        break;
                    }
                }
            }

            public static void luckyEvasion() {
                Random random = new Random();
                boolean eve = random.nextBoolean();
                for (int i = 0; i < heroesHealth.length ; i++){
                    if (heroesHealth[5] > 0)
                        if (eve){
                            heroesHealth[5] = heroesHealth[5] + 90;
                            System.out.println("lucky dodged damage");
                            break;
                        }
                }
            }

            public static void berserkRage(){

                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[6] > 0){
                        heroesHealth[6] -= bossDamage * 1 / 14;
                        heroesDamage[6] += heroesDamage[6] + bossDamage * 1 / 14;
                        System.out.println("berserk blocking damage and up damage");
                        break;
                    }

                }
            }

            public static void thorStanning(){
                Random random = new Random();
                boolean stun = random.nextBoolean();
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[7] > 0){
                        if(stun){
                            bossDamage = bossDamage - bossDamage;
                            System.out.println("The boss is stunned");
                            break;
                        }else{
                            bossDamage = 60;
                        }
                    }
                }

            }



            public static int round_number = 0;

            public static void main(String[] args) {
                printStatistics();
                while (!isGameFinished()) {
                    round();
                    medicHeal();
                    golemTanking();
                    luckyEvasion();
                    berserkRage();
                    thorStanning();

                }
            }

            public static void chooseBossDefence() {
                Random random = new Random();
                int randomIndex = random.nextInt(
                        heroesAttakTypes.length);
                bossDefence = heroesAttakTypes[randomIndex];
                System.out.println("Boss chose " + bossDefence);
            }

            public static void round() {
                round_number++;
                chooseBossDefence();
                if (bossHealth > 0) {
                    bossHits();
                }
                heroesHit();
                printStatistics();
            }

            public static boolean isGameFinished() {
                if (bossHealth <= 0) {
                    System.out.println("Heroes won!!!");
                    return true;
                }

                boolean allHeroesDead = true;
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        allHeroesDead = false;
                        break;
                    }
                }
                if (allHeroesDead) {
                    System.out.println("Boss won!!!");
                }
                return allHeroesDead;
            }
            public static void heroesHit() {
                for (int i = 0; i < heroesDamage.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        if (heroesAttakTypes[i] == bossDefence) {
                            Random random = new Random();
                            int coeff = random.nextInt(11);
                            if (bossHealth - heroesDamage[i] * coeff < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i] * coeff;
                            }
                            System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                        } else {
                            if (bossHealth - heroesDamage[i] < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i];
                            }
                        }
                    }


                }

            }

            public static void bossHits() {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        if (heroesHealth[i] - bossDamage < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage;
                        }
                    }

                }


            }

            public static void printStatistics() {
                System.out.println(round_number + " ROUND ______________");
                System.out.println("Boss health: " + bossHealth
                        + " (" + bossDamage + ")");
                for (int i = 0; i < heroesHealth.length; i++) {
                    System.out.println(heroesAttakTypes[i]
                            + " health: " + heroesHealth[i]
                            + " (" + heroesDamage[i] + ")");
                }
                System.out.println("____________________");


            }

        }


