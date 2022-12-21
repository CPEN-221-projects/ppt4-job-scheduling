package datacenter;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Processor {

    /*
        Abstraction Function:
            this represents a processor with time capacity this.timeLimit
            and holds the jobs in this.jobs
        Representation Invariant:
            jobs != null && jobs does not contain nulls
            && timeLimit > 0
            && (there should be something more that you should think about)
     */

    private List<Job> jobs;
    private int timeLimit;
    private int timeUsed;

    /**
     * Create a new empty processor
     * @param timeLimit the limit on compute time on this processor, > 0
     */
    public Processor(int timeLimit) {
        this.timeLimit = timeLimit;
        this.jobs = new ArrayList<Job>();
        this.timeUsed = 0;
    }

    /**
     * Check if a given job can fit in this processor
     *
     * @return true if adding the job does not exceed the time limit on this processor, and false otherwise.
     */
    public boolean canFitJob(Job job) {
        return job.getExecutionTime() <= timeLimit - timeUsed;
    }

    /** Inserts a job to the processor, at the end of its schedule
     *
     * @param job not null
     * @return true if the job can fit on this processor and was assigned, and false otherwise
     */
    public boolean addJob(Job job) {
        if (canFitJob(job)) {
            jobs.add(job);
            timeUsed += job.getExecutionTime();
            return true;
        }
        return false;
    }

    /** Get the peak memory usage of this processor
     *
     * @return the peak memory usage of the jobs ossigned to this processor
     * */
    public int getPeakMemoryUsage() {
        int maxMem = 0;
        for (Job job: jobs) {
            if (job.getMemoryUsage() > maxMem) {
                maxMem = job.getMemoryUsage();
            }
        }
        return maxMem;
    }

    /** Get the total computation (execution) time of this processor
     *
     * @return the total computation (execution) time of jobs assigned
     * to this processor
     */
    public int getTotalComputationTime() {
        return timeUsed;
    }

    /** Check if this processor is equal to a given processor
     *
     * @return true if both processors have exactly the same jobs,
     * in the same order, and they have the same time limit
     */
    public boolean equals(Processor that) {
        return Arrays.equals(getJobs(), that.getJobs()) && getTimeLimit() == that.getTimeLimit();
    }

    /** Get the time limit of this processor
     *
     * @return the time limit on this processor
     */
    public int getTimeLimit() {
        return this.timeLimit;
    }

    /**
     * Obtain the jobs scheduled on this processor, in scheduled order
     * @return the jobs scheduled on this processor, in scheduled order
     */
    public Job[] getJobs() {
        Job[] jobsArray = new Job[jobs.size()];
        for (int i = 0; i < jobsArray.length; i++) {
            jobsArray[i] = jobs.get(i);
        }

        return jobsArray;
    }
}