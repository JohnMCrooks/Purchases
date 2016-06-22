/*
 * Copyright (c) 2016.
 */

package com.crooks;

import org.springframework.data.domain.Range;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 6/22/16.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    public Iterable<Purchase> findByCategory (String Category);

}
