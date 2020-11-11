import java.util.Scanner;

public class N3 {
    public void n3() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество планет в системе: ");
        int countPlanet = in.nextInt();
        System.out.println("Введите название звезды в системе: ");
        String nameStar = in.next();
        StarSystem house = new StarSystem(countPlanet, nameStar);
        System.out.println(house);
    }
        public static class StarSystem {

            private int countPlanet;

            private String nameStar;

            StarSystem() {
                this.countPlanet = 0;
                this.nameStar = "";
            }

            public StarSystem(int countPlanet, String nameStar) {
                this.countPlanet = countPlanet;
                this.nameStar = nameStar;
            }

            public int getСountPlanet() {
                return countPlanet;
            }

            public void setСountPlanet(int countPlanet) {
                this.countPlanet = countPlanet;
            }

            public String getNameStar() {
                return nameStar;
            }

            public void setNameStar(String nameStar) {
                this.nameStar = nameStar;
            }

            public static class mainPlanet {
                private String Planet;

                public void setPlanet(String Planet) {
                    this.Planet = Planet;
                }

                public String getPlanet() {
                    return Planet;
                }
            }

            public static class mainStar {
                private String Star;

                public void setStar(String Star) {
                    this.Star = Star;
                }

                public String getStar() {
                    return Star;
                }
            }

            public static class mainMoon {
                private String Moon;

                public void setMoon(String Moon) {
                    this.Moon = Moon;
                }

                public String getMoon() {
                    return Moon;
                }
            }

            @Override
            public String toString() {
                return "Звездная система --- " +
                        "Количество планет = " + countPlanet +
                        ", Название звезды = " + nameStar;
            }
    }
}
