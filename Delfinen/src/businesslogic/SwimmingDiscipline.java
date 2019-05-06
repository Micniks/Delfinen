package businesslogic;

public enum SwimmingDiscipline {
    BUTTERFLY,
    CRAWL,
    RYGCRAWL,
    BRYSTSVØMMING;

    public SwimmingDiscipline getSwimmingDisciplineFromString(String str) {
        if (str.contains("BUTTERFLY")) {
            return BUTTERFLY;
        }
        if (str.contains("CRAWL")) {
            return CRAWL;
        }
        if (str.contains("RYGCRAWL")) {
            return RYGCRAWL;
        }
        if (str.contains("BRYSTSVØMMING")) {
            return BRYSTSVØMMING;
        }
        return null;
    }

}
