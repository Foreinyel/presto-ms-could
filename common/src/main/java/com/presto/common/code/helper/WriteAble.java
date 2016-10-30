package com.presto.common.code.helper;

import com.presto.common.log.LogAble;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Created by shihao on 16/10/29.
 */
public interface WriteAble extends LogAble {
    default void write(VelocityContext vc, String template, String output) {
        FileWriter out = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(template));
            out = new FileWriter(new File(output));

            Velocity.init();
            VelocityEngine ve = new VelocityEngine();
            ve.evaluate(vc, out, "velocity", inputStreamReader);
        } catch (Exception e) {
            log().error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (Exception e) {
                log().error(e.getMessage(), e);
            }
        }
    }
}
