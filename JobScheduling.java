package daa;
import java.util.*;

class Job {
    int id, deadline, profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobScheduling {

    public static void findMaxProfitSchedule(List<Job> jobs) {
        // Sort jobs by profit in descending order
        jobs.sort((a, b) -> b.profit - a.profit);

        // Find the maximum deadline to define the schedule array size
        int maxDeadline = jobs.stream().mapToInt(job -> job.deadline).max().orElse(0);

        // Array to keep track of job assignments
        int[] schedule = new int[maxDeadline];
        Arrays.fill(schedule, -1); // -1 indicates a free slot

        // Total profit accumulator
        int totalProfit = 0;

        // Schedule the jobs
        for (Job job : jobs) {
            // Try to assign the job to the latest available slot before its deadline
            for (int slot = Math.min(maxDeadline - 1, job.deadline - 1); slot >= 0; slot--) {
                if (schedule[slot] == -1) { // Slot is free
                    schedule[slot] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Output the result
        System.out.println("Optimal Job Sequence:");
        for (int slot = 0; slot < maxDeadline; slot++) {
            if (schedule[slot] != -1) {
                System.out.print("Job " + schedule[slot] + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of jobs
        System.out.print("Enter the number of jobs: ");
        int n = sc.nextInt();

        // Input the job details
        List<Job> jobs = new ArrayList<>();
        System.out.println("Enter Job ID, Deadline, and Profit:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int deadline = sc.nextInt();
            int profit = sc.nextInt();
            jobs.add(new Job(id, deadline, profit));
        }

        // Find and print the maximum profit schedule
        findMaxProfitSchedule(jobs);

        sc.close();
    }
}
