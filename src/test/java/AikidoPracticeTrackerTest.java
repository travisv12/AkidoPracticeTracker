import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AikidoPracticeTrackerTest {

    @Test
    void testAddPracticeSession() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(LocalDate.now(), 60);
        assertEquals(60, tracker.getTotalPracticeTime());
    }

    @Test
    void testGetTotalPracticeTime() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addPracticeSession(LocalDate.now(), 60);
        tracker.addPracticeSession(LocalDate.now().plusDays(1), 30);
        assertEquals(90, tracker.getTotalPracticeTime());
    }

    @Test
    void testIsEligibleForGraduation() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        LocalDate startDate = LocalDate.now().minusMonths(6);
        tracker.addPracticeSession(startDate, 60);
        assertTrue(tracker.isEligibleForGraduation());

        tracker = new AikidoPracticeTracker();
        for (int i = 0; i < 100; i++) {
            tracker.addPracticeSession(LocalDate.now().minusDays(i), 60);
        }
        assertTrue(tracker.isEligibleForGraduation());
    }
}