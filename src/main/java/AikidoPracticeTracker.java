import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AikidoPracticeTracker {
    private static class PracticeSession {
        LocalDate date;
        int duration; // in minutes

        PracticeSession(LocalDate date, int duration) {
            this.date = date;
            this.duration = duration;
        }
    }

    private List<PracticeSession> sessions = new ArrayList<>();
    private LocalDate startDate;

    public void addPracticeSession(LocalDate date, int duration) {
        if (sessions.isEmpty()) {
            startDate = date;
        }
        sessions.add(new PracticeSession(date, duration));
    }

    public int getTotalPracticeTime() {
        return sessions.stream().mapToInt(session -> session.duration).sum();
    }

    public boolean isEligibleForGraduation() {
        if (sessions.isEmpty()) {
            return false;
        }
        LocalDate now = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(startDate, now);
        return months >= 6 || sessions.size() >= 100;
    }

    public static void main(String[] args) {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Aikido Practice Tracker =====");
            System.out.println("1. Add Practice Session");
            System.out.println("2. View Total Practice Time");
            System.out.println("3. Check Graduation Eligibility");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter duration (minutes): ");
                    int duration = scanner.nextInt();
                    tracker.addPracticeSession(date, duration);
                    break;
                case 2:
                    System.out.println("Total Practice Time: " + tracker.getTotalPracticeTime() + " minutes");
                    break;
                case 3:
                    if (tracker.isEligibleForGraduation()) {
                        System.out.println("You are eligible for Kyu graduation.");
                    } else {
                        System.out.println("You are not eligible for Kyu graduation.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}