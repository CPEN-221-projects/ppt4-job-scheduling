package datacenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Placement {
    private List<Processor> processors;

    /** Create a new empty placement
     *
     */
    public Placement() {
        this.processors = new ArrayList<Processor>();
    }

    /** Add a processor to this placement
     *
     */
    public void addProcessor(Processor processor) {
        this.processors.add(processor);
    }

    /** Get the cost of this placement
     *
     *  @return cost
     */
    public int getCost() {
        int cost = 0;
        for (Processor processor: processors) {
            cost += processor.getPeakMemoryUsage();
        }
        return cost;
    }

    /**
     * Compute the makespan of this placement
     *
     * @return the makespan of the placement, and return 0, if there is no work
     * (no processors or no jobs on any processor)
     */
    public int getMakeSpan() {
        int makespan = 0;
        for (Processor processor: processors) {
            if (processor.getTotalComputationTime() > makespan) {
                makespan = processor.getTotalComputationTime();
            }
        }
        return makespan;
    }

    /** Check if this placement is equal to another given placement
     *
     * @param that is the other placement to check
     * @return true if (1) number of processors in "that" is
     * equal to the number of processors in "this", and (2) each processor
     * in "this" is equal to the corresponding processor in "that"
     * (order of processors does matter)
     * */
    public boolean equals(Placement that) {
        if (that.processors.size() != processors.size()) {
            return false;
        }
        for (int i = 0; i < processors.size(); i++) {
            if (!processors.get(i).equals(that.processors.get(i))) {
                return false;
            }
        }
        return true;
    }

    /** Obtain the mean flow time for this placement
     *
     * @return the mean flow time, and return 0 if there is no work (no processors or no jobs on any processor)
     */
    public double getMeanFlowTime() {
        int flowTimeSum = 0;
        int numJobs = 0;
        for (Processor processor: processors) {
            int processorTime = 0;
            for (Job job: processor.getJobs()) {
                processorTime += job.getExecutionTime();
                flowTimeSum += processorTime;
                numJobs++;
            }
        }
        return (double) flowTimeSum / numJobs;
    }

    /** Obtain the median flow time for this placement
     *
     * @return the median flow time, and return 0 if there is no work (no processors or no jobs on any processors)
     */
    public double getMedianFlowTime() {
        List<Integer> flowTimeList = new ArrayList<>();
        for (Processor processor: processors) {
            int processorTime = 0;
            for (Job job: processor.getJobs()) {
                processorTime += job.getExecutionTime();
                flowTimeList.add(processorTime);
            }
        }
        Collections.sort(flowTimeList);

        if (flowTimeList.size() % 2 == 0) {
            return (double) (flowTimeList.get(flowTimeList.size() / 2 - 1) +
                    flowTimeList.get(flowTimeList.size() / 2)) / 2;
        } else {
            return (double) flowTimeList.get(flowTimeList.size() / 2);
        }
    }

}