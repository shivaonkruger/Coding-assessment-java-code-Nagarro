import java.util.*;

public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        
        Map<Character, Integer> freq = new HashMap<>();
        for (char task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        //This creates a frequency map that counts how many times each task appears in the input array. The key is the task (a character) and the value is the count of that task.

        Map<Character, Integer> lastRun = new HashMap<>();
        //This map keeps track of the last time each task was executed. The key is the task and the value is the time unit when it was last run.
        int time = 0;

        while (hasRemainingTasks(freq)) {
            time++;
            char bestTask = ' ';
            int bestFreq = 0;
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                char task = entry.getKey();
                int count = entry.getValue();
                

                if (count <= 0) continue;
                // If the count of the task is zero or negative, it means we have already executed all instances of that task, so we skip it.

                if (lastRun.containsKey(task) && (time - lastRun.get(task)) <= n) {
                    continue; 
                }
                // The if statement above checks if the task has been executed before and if it is still in the cooldown period. If both conditions are true, we skip this task for the current time unit.
                if (count > bestFreq) {
                    bestFreq = count;
                    bestTask = task;
                }
                //This whole function is to extract the task with the highest frequency that can be executed at the current given time. We check if the task can be executed (i.e., it has remaining count and is not in the cooldown period) and if it has a higher frequency than the best task found so far.
                // If frequencies are the same, we can choose any task. Here we just keep the first one we find.
            }


            if (bestTask != ' ') {
                freq.put(bestTask, freq.get(bestTask) - 1);
                lastRun.put(bestTask, time);
                System.out.println("Time " + time + ": Run " + bestTask);
            } else {
                System.out.println("Time " + time + ": IDLE");
            }
        }

        return time;
    }
    private static boolean hasRemainingTasks(Map<Character, Integer> freq) {
        for (int count : freq.values()) {
            if (count > 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println("=== Example 1: tasks=[A,A,A,B,B,B], n=2 ===");
        char[] tasks1 = {'A','A','A','B','B','B'};
        int result1 = leastInterval(tasks1, 2);
        System.out.println("Total Time to execute all tasks: " + result1);
    }
}