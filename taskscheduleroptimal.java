public class taskscheduleroptimal {
     public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char t : tasks) {
            freq[t - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int countMaxFreq = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                countMaxFreq++;
            }
        }

        int frameSize = (maxFreq - 1) * (n + 1) + countMaxFreq;

        return Math.max(tasks.length, frameSize);
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        System.out.println(leastInterval(tasks, n));
    }
}
