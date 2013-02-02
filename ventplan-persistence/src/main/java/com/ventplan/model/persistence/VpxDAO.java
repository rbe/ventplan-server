/*
 * ventplan-server
 * ventplan-persistence
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 07.01.13 15:39
 */

package com.ventplan.model.persistence;

import eu.artofcoding.beetlejuice.cdm.Base;
import eu.artofcoding.beetlejuice.persistence.GenericDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateless
public class VpxDAO extends GenericDAO<Base> {

    private static final Logger logger = Logger.getLogger(VpxDAO.class.getName());

    @PersistenceContext
    private EntityManager em;

    public VpxDAO() {
        super(null);
    }

    @PostConstruct
    private void postConstruct() {
        setEntityManager(em);
    }

}
