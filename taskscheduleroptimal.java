public class taskscheduleroptimal {
     public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char t : tasks) {
            freq[t - 'A']++;
        }
        //to create a frequency array that counts how many times each task appears in the input array. The index of the array corresponds to the task and the value at each index is the count of that task.

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
        // The above code calculates the frequency of each task and finds the maximum frequency (maxFreq) and counts how many tasks have that maximum frequency (countMaxFreq). This information is crucial for determining the structure of the schedule and how many idle slots are needed.

        int frameSize = (maxFreq - 1) * (n + 1) + countMaxFreq;
        //This is the formualae to calculate the minimum time required to execute all tasks considering the cooldown period. The formula is derived from the idea of creating "frames" of tasks where each frame can hold one instance of the most frequent task and up to n other tasks (or idle slots) in between. The number of frames is determined by the frequency of the most common task, and we add the count of tasks that have the same maximum frequency to account for them in the last frame.

        return Math.max(tasks.length, frameSize);
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }
}
