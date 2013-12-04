/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.agoncal.book.javaee7.chapter02.ex23;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用于测试logger，结果entering和exiting根本无效
 * @author admin
 */
public class TestLog {

    private static final Logger LOG = Logger.getLogger(TestLog.class.getName());

    private   void test() {
//        LOG.setLevel(Level.ALL);
        LOG.entering(this.getClass().getCanonicalName(), "test");
//        LOG.info("--info--");
        LOG.exiting(this.getClass().getCanonicalName(), "test");
    }
    
    public static void main(String[] args) {
        TestLog t = new TestLog();
        t.test();
    }
}
