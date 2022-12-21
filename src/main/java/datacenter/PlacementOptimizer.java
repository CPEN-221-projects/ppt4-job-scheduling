package datacenter;

import java.util.*;

public class PlacementOptimizer {

    /*
     * @param jobs a list of jobs. Does not contain
     * null elements and is not null
     *
     * @param timeLimitPerProcessor the maximum computation time
     * per processor. This is the same for all processors.
     *
     * @return a Placement object representing the optimal placement of
     * jobs to processors such that the total cost is minimized.
     * The cost of a processor is the peak memory usage at that processor.
     */

    /*
     * Find maximum job length. then, look around that job and see if you can contain the 2nd
     * highest job in the subarray, and loop so on
     *
     * if you CAN place the 2nd highest job in that subarray, then see if you can
     * place the 3rd highest in the same subarray
     *
     * you stop when you can't add anything more
     *
     * then repeat for the next highest job remaining, and add new processor
     *
     */
    public static Placement arrange(List<Job> jobs,
                                    int timeLimitPerProcessor) {

        List<Job> availableJobs = new ArrayList<>(jobs);

        Placement optimalPlacement = new Placement();

        while (!availableJobs.isEmpty()) {
            Processor processor = new Processor(timeLimitPerProcessor);
            List<Job> jobList = maxSubarray(availableJobs);
            for (Job thisJob: jobList) {
                availableJobs.remove(thisJob);
                processor.addJob(thisJob);
            }
            optimalPlacement.addProcessor(processor);
        }

        return optimalPlacement;
    }

    private static List<Job> maxSubarray(List<Job> availableJobs) {
        Job maxJob = availableJobs.get(0);
        for (Job job: availableJobs) {
            if (job.getMemoryUsage() > maxJob.getMemoryUsage()) {
                maxJob = job;
            }
        }

        int totalSubTime = 0;
        for


        return null;
    }

}
