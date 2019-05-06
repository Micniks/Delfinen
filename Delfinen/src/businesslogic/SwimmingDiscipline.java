package businesslogic;

public enum SwimmingDiscipline {
    BUTTERFLY,
    CRAWL,
    RYGCRAWL,
    BRYSTSVØMNING;

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
        if (str.contains("BRYSTSVØMNING")) {
            return BRYSTSVØMNING;
        }
        return null;
    }

}
