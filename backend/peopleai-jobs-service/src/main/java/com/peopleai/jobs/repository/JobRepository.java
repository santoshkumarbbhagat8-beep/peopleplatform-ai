package com.peopleai.jobs.repository;
import com.peopleai.jobs.model.Job;import org.springframework.data.jpa.repository.JpaRepository;
public interface JobRepository extends JpaRepository<Job,Long>{}
