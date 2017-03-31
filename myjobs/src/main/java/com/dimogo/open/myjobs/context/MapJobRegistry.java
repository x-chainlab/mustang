package com.dimogo.open.myjobs.context;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.util.Assert;

import java.util.*;

public class MapJobRegistry implements JobRegistry {
    private Map<String, JobFactory> map = new HashMap<String, JobFactory>();

    public void register(JobFactory jobFactory) throws DuplicateJobException {
        Assert.notNull(jobFactory);
        String name = jobFactory.getJobName();
        Assert.notNull(name, "Job configuration must have a name.");
        synchronized (map) {
            if (map.containsKey(name)) {
                throw new DuplicateJobException("A job configuration with this name [" + name
                        + "] was already registered");
            }
            map.put(name, jobFactory);
        }
    }

    public void unregister(String name) {
        Assert.notNull(name, "Job configuration must have a name.");
        synchronized (map) {
            map.remove(name);
        }
    }

    public Job getJob(String name) throws NoSuchJobException {
        synchronized (map) {
            if (!map.containsKey(name)) {
                throw new NoSuchJobException("No job configuration with the name [" + name + "] was registered");
            }
            return map.get(name).createJob();
        }
    }

    public Collection<String> getJobNames() {
        synchronized (map) {
            return Collections.unmodifiableCollection(new HashSet<String>(map.keySet()));
        }
    }

}
