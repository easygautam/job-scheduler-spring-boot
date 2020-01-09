package com.easygautam.exjpa.repository;

import com.easygautam.exjpa.entity.AdJob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdJobRepository extends JpaRepository<AdJob, Long> {

    List<AdJob> findAllByType(AdJob.Type type);

}
