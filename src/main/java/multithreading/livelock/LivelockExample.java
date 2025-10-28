package multithreading.livelock;

public class LivelockExample {
    static class CaseFile {
        private Detective investigator;
        public CaseFile(Detective d) { investigator = d; }
        public synchronized Detective getInvestigator() { return investigator; }
        public synchronized void setInvestigator(Detective d) { investigator = d; }
    }

    static class Detective {
        private String name;

        public Detective(String name) { this.name = name; }

        public void investigate(CaseFile caseFile, Detective partner) {
            while (true) {
                if (caseFile.getInvestigator() != this) continue;

                System.out.println("You investigate, " + partner.name);
                caseFile.setInvestigator(partner);
            }
        }
    }

    public static void main(String[] args) {
        Detective detective1 = new Detective("Holmes");
        Detective detective2 = new Detective("Watson");
        CaseFile caseFile = new CaseFile(detective1);
        new Thread(() -> detective1.investigate(caseFile, detective2)).start();
        new Thread(() -> detective2.investigate(caseFile, detective1)).start();
    }
}
